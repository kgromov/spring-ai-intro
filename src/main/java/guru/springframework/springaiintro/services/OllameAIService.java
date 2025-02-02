package guru.springframework.springaiintro.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

//@Primary
@Service
@Profile({"ollama", "local", "default", "!openai"})
public class OllameAIService extends AiService {

    public OllameAIService(@Qualifier("ollamaChatClientBuilder") ChatClient.Builder builder,
                           @Qualifier("ollamaChatClient") ChatClient chatClient) {
        super(builder, chatClient);
    }
}
