package guru.springframework.springaiintro.config;

import guru.springframework.springaiintro.services.MetadataAdvisor;
import org.springframework.ai.autoconfigure.azure.openai.AzureOpenAiAutoConfiguration;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Profile({"azure", "default"})
@Configuration
@Import(AzureOpenAiAutoConfiguration.class)
public class AzureConfig {

    @Value("classpath:prompts/capital-prompt.st")
    private Resource capitalPrompt;

    @Bean
    public ChatClient.Builder azureChatClientBuilder(AzureOpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }

    @Bean
    public ChatClient azureChatClient(AzureOpenAiChatModel chatModel, MetadataAdvisor metadataAdvisor) {
        return ChatClient.builder(chatModel)
                .defaultUser(capitalPrompt)
                .defaultAdvisors(metadataAdvisor)
                .build();
    }
}
