package guru.springframework.springaiintro.config;

import guru.springframework.springaiintro.services.MetadataAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.autoconfigure.ollama.OllamaAutoConfiguration;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;

@Profile({"ollama", "local", "default", "!openai"})
@Configuration
@Import(OllamaAutoConfiguration.class)
@RequiredArgsConstructor
public class OllamaConfig {

    @Value("classpath:prompts/capital-prompt.st")
    private Resource capitalPrompt;

    @Value("classpath:prompts/capital-with-info.st")
    private Resource capitalPromptWithInfo;

    @Bean
    public ChatClient.Builder ollamaChatClientBuilder(OllamaChatModel chatModel) {
        return ChatClient.builder(chatModel);
    }

    @Bean
    public ChatClient ollamaChatClient(OllamaChatModel chatModel, MetadataAdvisor metadataAdvisor) {
        return  ChatClient.builder(chatModel)
                .defaultUser(capitalPrompt)
                .defaultAdvisors(metadataAdvisor)
                .build();
    }
}
