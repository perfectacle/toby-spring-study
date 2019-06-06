package example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionMakerFactory {
    @Bean
    public static ConnectionMaker dConnectionMaker() {
        return new DConnectionMaker();
    }

    @Bean
    public static ConnectionMaker nConnectionMaker() {
        return new NConnectionMaker();
    }
}
