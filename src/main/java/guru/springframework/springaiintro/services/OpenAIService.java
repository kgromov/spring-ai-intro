package guru.springframework.springaiintro.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("openai")
public class OpenAIService extends AiService {

    public OpenAIService(@Qualifier("openAiChatClientBuilder") ChatClient.Builder builder,
                         @Qualifier("openAiChatClient") ChatClient chatClient) {
        super(builder, chatClient);
    }
}
