package guru.springframework.springaiintro.config;

import guru.springframework.springaiintro.services.MetadataAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.model.openai.autoconfigure.OpenAiChatAutoConfiguration;
import org.springframework.ai.model.openai.autoconfigure.OpenAiChatProperties;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Profile({"openai", "lm-studio",  "gemini", "nvidia"})
@Configuration
@Import(OpenAiChatAutoConfiguration.class)
public class OpenAiConfig {

    @Value("classpath:prompts/capital-prompt.st")
    private Resource capitalPrompt;

    @Bean
    public ChatClient.Builder openAiChatClientBuilder(OpenAiChatModel chatModel, OpenAiChatProperties chatProperties) {
        return ChatClient.builder(chatModel)
                .defaultOptions(OpenAiChatOptions.builder()
                        .apiKey(chatProperties.getOptions().getApiKey())
                        .baseUrl(chatProperties.getOptions().getBaseUrl())
                );
    }

    @Bean
    public ChatClient openAiChatClient(ChatClient.Builder chatClientBuilder, MetadataAdvisor metadataAdvisor) {
        return chatClientBuilder
                .defaultUser(capitalPrompt)
                .defaultAdvisors(metadataAdvisor)
                .build();
    }
}
