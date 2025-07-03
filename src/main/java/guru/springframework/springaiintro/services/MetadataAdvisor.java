package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.observability.SpringAiMetricsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor.DEFAULT_REQUEST_TO_STRING;
import static org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor.DEFAULT_RESPONSE_TO_STRING;

@Component
@RequiredArgsConstructor
public class MetadataAdvisor implements CallAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(MetadataAdvisor.class);

    private final SpringAiMetricsService metricsService;

    @Getter
    private final Map<String, ChatResponseMetadata> metadataMap = new ConcurrentHashMap<>();

    @Override
    public ChatClientResponse adviseCall(ChatClientRequest request, CallAdvisorChain chain) {
        logger.debug("request: {}", DEFAULT_REQUEST_TO_STRING.apply(request));
        var now = Instant.now();
        var advisedResponse = chain.nextCall(request);
        logger.debug("response: {}", DEFAULT_RESPONSE_TO_STRING.apply(advisedResponse.chatResponse()));
        var metadata = advisedResponse.chatResponse().getMetadata();
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
