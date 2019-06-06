package example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SomeContainer3 {
    @Bean
    public SomeObject someObject() {
        return new SomeObject(someObjectOfObject());
    }

    @Bean
    public SomeObjectOfObject someObjectOfObject() {
        return new SomeObjectOfObject();
    }
}
