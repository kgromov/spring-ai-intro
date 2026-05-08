package guru.springframework.springaiintro.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"ollama"})
public class OllamaAIService extends AiService {

    public OllamaAIService(@Qualifier("ollamaChatClientBuilder") ChatClient.Builder builder,
                           @Qualifier("ollamaChatClient") ChatClient chatClient) {
        super(builder, chatClient);
    }
}
