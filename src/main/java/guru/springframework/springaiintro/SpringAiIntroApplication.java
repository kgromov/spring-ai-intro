package guru.springframework.springaiintro;

import org.springframework.ai.autoconfigure.ollama.OllamaAutoConfiguration;
import org.springframework.ai.autoconfigure.openai.OpenAiAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {OpenAiAutoConfiguration.class, OllamaAutoConfiguration.class})
public class SpringAiIntroApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiIntroApplication.class, args);
    }

}
