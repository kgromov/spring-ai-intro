package guru.springframework.springaiintro.config;

import guru.springframework.springaiintro.services.MetadataAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    @Value("classpath:prompts/capital-prompt.st")
    private Resource capitalPrompt;

    @Value("classpath:prompts/capital-with-info.st")
    private Resource capitalPromptWithInfo;

    @Bean
    ChatClient chatClient(ChatClient.Builder builder, MetadataAdvisor metadataAdvisor) {
        return builder
                .defaultUser(capitalPrompt)
                .defaultAdvisors(metadataAdvisor)
                .build();
    }

    // More verbose version
//    @Bean
    ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultUser(capitalPrompt)
                .build();
    }
}
