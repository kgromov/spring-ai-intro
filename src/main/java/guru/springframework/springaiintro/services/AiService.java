package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.client.ChatClient;

public abstract class AiService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    protected final ChatClient.Builder builder;
    protected final ChatClient chatClient;

    protected AiService(ChatClient.Builder builder, ChatClient chatClient) {
        this.builder = builder;
        this.chatClient = chatClient;
    }

    public CapitalInfoResponse getCapitalWithInfo(CapitalRequest capitalRequest) {
        log.info("Requesting capital with extended info for {}", capitalRequest.stateOrCountry());
        return chatClient.prompt()
                .user(up -> up.param("stateOrCountry", capitalRequest.stateOrCountry()))
                .call()
                .entity(CapitalInfoResponse.class);
    }

    public CapitalResponse getCapital(CapitalRequest capitalRequest) {
        log.info("Requesting capital for {}", capitalRequest.stateOrCountry());
        return chatClient.prompt()
                .user(up -> up.param("stateOrCountry", capitalRequest.stateOrCountry()))
                .call()
                .entity(CapitalResponse.class);
    }

    public Answer getAnswer(Question question) {
        log.info("Answering question: {}", question.question());
        String answer = this.getAnswer(question.question());
        return new Answer(answer);
    }

    public String getAnswer(String question) {
        log.info("Answering question: {}", question);
        return builder
                .build()
                .prompt()
                .user(question)
                .call()
                .content();
    }
}
