package example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SomeContainer4 {
    @Bean
    public SomeObject someObject(final SomeObjectOfObject someObjectOfObject) {
        return new SomeObject(someObjectOfObject);
    }

    @Bean
    public SomeObjectOfObject someObjectOfObject() {
        return new SomeObjectOfObject();
    }
}
