package guru.springframework.springaiintro.observability;

import guru.springframework.springaiintro.services.MetadataAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.metadata.ChatResponseMetadata;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Endpoint(id="springAiMetadata")
@RequiredArgsConstructor
public class SpringAiMetadataEndpoint {
    private final MetadataAdvisor metadataAdvisor;

    @ReadOperation
    public Map<String, ChatResponseMetadata> getMetadata() {
        return metadataAdvisor.getMetadataMap();
    }
}
