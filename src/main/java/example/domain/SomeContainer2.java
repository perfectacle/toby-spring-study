package example.domain;

import org.springframework.context.annotation.Bean;

public class SomeContainer2 {
    @Bean
    public SomeObject someObject(final SomeObjectOfObject someObjectOfObject) {
        return new SomeObject(someObjectOfObject);
    }

    @Bean
    public SomeObjectOfObject someObjectOfObject() {
        return new SomeObjectOfObject();
    }
}
