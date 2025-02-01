package guru.springframework.springaiintro.controllers;

import guru.springframework.springaiintro.model.*;
import guru.springframework.springaiintro.services.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class QuestionController {

    private final AiService aiService;

    @PostMapping("/capitalWithInfo")
    public CapitalInfoResponse getCapitalWithInfo(@RequestBody CapitalRequest capitalRequest) {
        return this.aiService.getCapitalWithInfo(capitalRequest);
    }

    @PostMapping("/capital")
    public CapitalResponse getCapital(@RequestBody CapitalRequest capitalRequest) {
        return this.aiService.getCapital(capitalRequest);
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
        return aiService.getAnswer(question);
    }
}
