package guru.springframework.springaiintro.services;

import guru.springframework.springaiintro.model.*;

public interface OpenAIService {

    CapitalInfoResponse getCapitalWithInfo(CapitalRequest capitalRequest);

    CapitalResponse getCapital(CapitalRequest capitalRequest);

    String getAnswer(String question);

    Answer getAnswer(Question question);
}
