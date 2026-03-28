package guru.springframework.springaiintro.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile({"anthropic"})
public class AnthropicAIService extends AiService {

    public AnthropicAIService(@Qualifier("anthropicChatClientBuilder") ChatClient.Builder builder,
                              @Qualifier("anthropicChatClient") ChatClient chatClient) {
        super(builder, chatClient);
    }
}
