package guru.springframework.springaiintro;

import org.springframework.ai.model.anthropic.autoconfigure.AnthropicChatAutoConfiguration;
import org.springframework.ai.model.ollama.autoconfigure.OllamaChatAutoConfiguration;
import org.springframework.ai.model.ollama.autoconfigure.OllamaEmbeddingAutoConfiguration;
import org.springframework.ai.model.openai.autoconfigure.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        OpenAiChatAutoConfiguration.class,
        OpenAiImageAutoConfiguration.class,
        OpenAiAudioSpeechAutoConfiguration.class,
        OpenAiAudioTranscriptionAutoConfiguration.class,
        OpenAiEmbeddingAutoConfiguration.class,
        OpenAiModerationAutoConfiguration.class,

        OllamaChatAutoConfiguration.class,
        OllamaEmbeddingAutoConfiguration.class,
//        OllamaApiAutoConfiguration.class

        AnthropicChatAutoConfiguration.class
})
public class SpringAiIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiIntroApplication.class, args);
    }

}


