package example.domain;

import org.springframework.context.annotation.Bean;

public class SomeContainer {
    @Bean
    public SomeObject someObject() {
        return new SomeObject(someObjectOfObject());
    }

    @Bean
    public SomeObjectOfObject someObjectOfObject() {
        return new SomeObjectOfObject();
    }
}
