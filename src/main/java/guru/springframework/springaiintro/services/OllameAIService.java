package guru.springframework.springaiintro.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@Profile({"ollama", "local", "default", "dev", "!openai"})
public class OllameAIService extends AiService {

    public OllameAIService(@Qualifier("ollamaChatClientBuilder") ChatClient.Builder builder,
                           @Qualifier("ollamaChatClient") ChatClient chatClient) {
        super(builder, chatClient);
    }
}
