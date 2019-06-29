package example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TxFactoryBeanConfig {
    @Bean
    public UserService userService(final UserServiceImpl userServiceImpl,
                                   final PlatformTransactionManager dataSourceTransactionManager) {
        return (UserService) new TxFactoryBean(userServiceImpl, dataSourceTransactionManager, "upgradeLevels", UserService.class).getObject();
    }
}
