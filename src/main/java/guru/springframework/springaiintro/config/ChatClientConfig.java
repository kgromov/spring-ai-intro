package guru.springframework.springaiintro.config;

import guru.springframework.springaiintro.services.MetadataAdvisor;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.openai.OpenAiChatModel;
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
