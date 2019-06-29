package example.domain;

import com.sun.jdi.InvocationException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TransactionHandler implements InvocationHandler {
    private final Object target;
    private final PlatformTransactionManager transactionManager;
    private String pattern;

    public TransactionHandler(final Object target,
                              final PlatformTransactionManager transactionManager,
                              final String pattern) {
        this.target = target;
        this.transactionManager = transactionManager;
        this.pattern = pattern;
    }

    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        if(method.getName().startsWith(pattern)) {
            return invokeTransaction(method, args);
        }
        return method.invoke(target, args);
    }

    private Object invokeTransaction(final Method method, final Object[] args) throws Throwable {
        final TransactionStatus transaction = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            final Object returnValue = method.invoke(target, args);
            this.transactionManager.commit(transaction);
            return returnValue;
        } catch (final InvocationTargetException e) {
            this.transactionManager.rollback(transaction);
            throw e.getTargetException();
        }
    }
}
