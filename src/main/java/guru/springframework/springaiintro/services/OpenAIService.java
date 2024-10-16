package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenAIService {
    private final ChatModel chatModel;
    private final ChatClient chatClient;

   
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
        return ChatClient.builder(chatModel)
                .build()
                .prompt()
                .user(question)
                .call()
                .content();
    }
}
