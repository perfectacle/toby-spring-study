package example.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class TransactionManagerFactory {
    @Bean
    public PlatformTransactionManager dataSourceTransactionManager(final DataSource dataSourceBean) {
        return new DataSourceTransactionManager(dataSourceBean);
    }
}
