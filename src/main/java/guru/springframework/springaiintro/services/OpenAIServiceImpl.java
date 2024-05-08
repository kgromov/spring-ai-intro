package guru.springframework.springaiintro.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.CapitalRequest;
import guru.springframework.springaiintro.model.CapitalResponse;
import guru.springframework.springaiintro.model.Question;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OpenAIServiceImpl implements OpenAIService {
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    @Value("classpath:templates/capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/capital-with-info.st")
    private Resource getCapitalPromptWithInfo;

    @Override
    public Answer getCapitalWithInfo(CapitalRequest capitalRequest) {
        PromptTemplate promptTemplate = new PromptTemplate(getCapitalPromptWithInfo);
        Prompt prompt = promptTemplate.create(Map.of("stateOrCountry", capitalRequest.stateOrCountry()));
        ChatResponse response = chatClient.call(prompt);
        return new Answer(response.getResult().getOutput().getContent());
    }

    @Override
    public CapitalResponse getCapital(CapitalRequest capitalRequest) {
        var parser = new BeanOutputParser<>(CapitalResponse.class);
        var promptTemplate = new PromptTemplate(getCapitalPrompt);
        Prompt prompt = promptTemplate.create(
                Map.of(
                        "stateOrCountry", capitalRequest.stateOrCountry(),
                        "format", parser.getFormat()
                )
        );
        ChatResponse response = chatClient.call(prompt);
        return parser.parse(response.getResult().getOutput().getContent());
    }

    @Override
    public Answer getAnswer(Question question) {
        String answer = this.getAnswer(question.question());
        return new Answer(answer);
    }

    @Override
    public String getAnswer(String question) {
        log.info("I was called");
        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse response = chatClient.call(prompt);
        return response.getResult().getOutput().getContent();
    }
}
