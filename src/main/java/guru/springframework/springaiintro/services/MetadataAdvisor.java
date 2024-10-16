package guru.springframework.springaiintro.services;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.advisor.api.AdvisedRequest;
import org.springframework.ai.chat.client.advisor.api.AdvisedResponse;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAroundAdvisorChain;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.ai.chat.metadata.RateLimit;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor.DEFAULT_REQUEST_TO_STRING;
import static org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor.DEFAULT_RESPONSE_TO_STRING;

@Component
public class MetadataAdvisor implements CallAroundAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(MetadataAdvisor.class);

    @Getter
    private final Map<String, ChatResponseMetadata> metadataMap = new ConcurrentHashMap<>();

    @Override
    public AdvisedResponse aroundCall(AdvisedRequest advisedRequest, CallAroundAdvisorChain chain) {
        logger.debug("request: {}", DEFAULT_REQUEST_TO_STRING.apply(advisedRequest));
        var advisedResponse = chain.nextAroundCall(advisedRequest);
        logger.debug("response: {}", DEFAULT_RESPONSE_TO_STRING.apply(advisedResponse.response()));
        try {
            var metadata = advisedResponse.response().getMetadata();
            metadataMap.put(metadata.getId(), metadata);
            // collect metadata
        } finally {
            return advisedResponse;
        }
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
