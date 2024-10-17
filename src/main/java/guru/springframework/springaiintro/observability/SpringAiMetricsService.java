package guru.springframework.springaiintro.observability;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Component
public class SpringAiMetricsService {

    private final Counter requestTokens;
    private final Timer responseTimer;

    public SpringAiMetricsService(MeterRegistry registry) {
        //        Counter requestTokens = registry.counter("spring.ai.request_tokens");
        requestTokens = Counter.builder("spring.ai.request_tokens")
                .description("Number of request tokens used")
                .tag("spring.ai", "request_tokens")
                .register(registry);
        responseTimer = Timer.builder("spring.ai.response_time")
                .tag("spring.ai", "response_time")
                .description("Cumulative response time in milliseconds")
                .register(registry);
    }

    public void incrementRequestTokens(Usage usage) {
        requestTokens.increment(usage.getTotalTokens());
    }

    public void recordResponseTime(Instant startTime) {
        responseTimer.record(Duration.between(startTime, Instant.now()).toMillis(), TimeUnit.MILLISECONDS);
    }
}
