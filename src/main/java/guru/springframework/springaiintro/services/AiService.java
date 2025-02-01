package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;

public abstract class AiService {
    private static final Logger log = LoggerFactory.getLogger(AiService.class);
    protected final ChatClient.Builder builder;
    protected final ChatClient chatClient;

    protected AiService(ChatClient.Builder builder, ChatClient chatClient) {
        this.builder = builder;
        this.chatClient = chatClient;
    }


    public CapitalInfoResponse getCapitalWithInfo(CapitalRequest capitalRequest) {
        return chatClient.prompt()
                .user(up -> up.param("stateOrCountry", capitalRequest.stateOrCountry()))
                .call()
                .entity(CapitalInfoResponse.class);
    }

    public CapitalResponse getCapital(CapitalRequest capitalRequest) {
        return chatClient.prompt()
                .user(up -> up.param("stateOrCountry", capitalRequest.stateOrCountry()))
                .call()
                .entity(CapitalResponse.class);
    }

    public Answer getAnswer(Question question) {
        String answer = this.getAnswer(question.question());
        return new Answer(answer);
    }

    public String getAnswer(String question) {
        log.info("I was called");
        return builder
                .build()
                .prompt()
                .user(question)
                .call()
                .content();
    }
}
