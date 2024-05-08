package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.Answer;
import guru.springframework.springaiintro.model.CapitalRequest;
import guru.springframework.springaiintro.model.CapitalResponse;
import guru.springframework.springaiintro.model.Question;

public interface OpenAIService {

    Answer getCapitalWithInfo(CapitalRequest capitalRequest);

    CapitalResponse getCapital(CapitalRequest capitalRequest);

    String getAnswer(String question);

    Answer getAnswer(Question question);
}
