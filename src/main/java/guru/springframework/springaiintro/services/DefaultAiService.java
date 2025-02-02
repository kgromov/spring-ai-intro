package guru.springframework.springaiintro.services;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Primary
@Service
@Profile({"default", "azure"})
public class DefaultAiService extends AiService {

    public DefaultAiService(@Qualifier("azureChatClientBuilder") ChatClient.Builder builder,
                            @Qualifier("azureChatClient") ChatClient chatClient) {
        super(builder, chatClient);
    }
}
