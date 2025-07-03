package guru.springframework.springaiintro;

import org.springframework.ai.model.azure.openai.autoconfigure.AzureOpenAiAudioTranscriptionAutoConfiguration;
import org.springframework.ai.model.azure.openai.autoconfigure.AzureOpenAiChatAutoConfiguration;
import org.springframework.ai.model.azure.openai.autoconfigure.AzureOpenAiEmbeddingAutoConfiguration;
import org.springframework.ai.model.azure.openai.autoconfigure.AzureOpenAiImageAutoConfiguration;
import org.springframework.ai.model.ollama.autoconfigure.OllamaApiAutoConfiguration;
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

        AzureOpenAiChatAutoConfiguration.class,
        AzureOpenAiImageAutoConfiguration.class,
        AzureOpenAiAudioTranscriptionAutoConfiguration.class,
        AzureOpenAiEmbeddingAutoConfiguration.class,

        OllamaChatAutoConfiguration.class,
        OllamaEmbeddingAutoConfiguration.class
//        OllamaApiAutoConfiguration.class
})
public class SpringAiIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiIntroApplication.class, args);
    }

}


