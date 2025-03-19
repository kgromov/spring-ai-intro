package guru.springframework.springaiintro.config;

import guru.springframework.springaiintro.services.MetadataAdvisor;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Profile({"openai", "deepseek", "gemini"})
@Configuration
@Import(OpenAiAutoConfiguration.class)
public class OpenAiConfig {

    @Value("classpath:prompts/capital-prompt.st")
    private Resource capitalPrompt;

    @Bean
    public ChatClient.Builder openAiChatClientBuilder(OpenAiChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }

    @Bean
    public ChatClient openAiChatClient(OpenAiChatModel chatModel, MetadataAdvisor metadataAdvisor) {
        return  ChatClient.builder(chatModel)
                .defaultUser(capitalPrompt)
                .defaultAdvisors(metadataAdvisor)
                .build();
    }
}
