package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.observability.SpringAiMetricsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor.DEFAULT_REQUEST_TO_STRING;
import static org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor.DEFAULT_RESPONSE_TO_STRING;

@Component
@RequiredArgsConstructor
public class MetadataAdvisor implements CallAroundAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(MetadataAdvisor.class);

    private final SpringAiMetricsService metricsService;

    @Getter
    private final Map<String, ChatResponseMetadata> metadataMap = new ConcurrentHashMap<>();

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        logger.debug("request: {}", DEFAULT_REQUEST_TO_STRING.apply(advisedRequest));
        var now = Instant.now();
        var advisedResponse = chain.nextAroundCall(advisedRequest);
        logger.debug("response: {}", DEFAULT_RESPONSE_TO_STRING.apply(advisedResponse.response()));
        var metadata = advisedResponse.response().getMetadata();
        metricsService.recordResponseTime(now);
        metricsService.incrementRequestTokens(metadata.getUsage());
        metadataMap.put(metadata.getId(), metadata);
        return advisedResponse;
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
