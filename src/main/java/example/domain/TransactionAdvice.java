package example.domain;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
public class TransactionAdvice implements MethodInterceptor {
    private final PlatformTransactionManager transactionManager;

    public TransactionAdvice(final PlatformTransactionManager dataSourceTransactionManager) {
        this.transactionManager = dataSourceTransactionManager;
    }

    @Override
    public Object invoke(final MethodInvocation invocation) throws Throwable {
        final TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

        try {
            final Object returnValue = invocation.proceed();
            transactionManager.commit(status);
            return returnValue;
        } catch (final RuntimeException e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
