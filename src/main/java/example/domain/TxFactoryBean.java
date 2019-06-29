package example.domain;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import java.lang.reflect.Proxy;

@Component
public class TxFactoryBean implements FactoryBean<Object> {
    private Object target;
    private PlatformTransactionManager transactionManager;
    private String pattern;
    private Class<?> serviceInterface;

    // 빈 생성 전용 생성자
    public TxFactoryBean() {}

    public TxFactoryBean(Object target,
                         PlatformTransactionManager transactionManager,
                         String pattern,
                         Class<?> serviceInterface) {
        this.target = target;
        this.transactionManager = transactionManager;
        this.pattern = pattern;
        this.serviceInterface = serviceInterface;
    }

    public void setTarget(final Object target) {
        this.target = target;
    }

    public void setTransactionManager(final PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setPattern(final String pattern) {
        this.pattern = pattern;
    }

    public void setServiceInterface(final Class<?> serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    @Override
    public Object getObject() {
        final TransactionHandler transactionHandler = new TransactionHandler(target, transactionManager, pattern);

        return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {serviceInterface}, transactionHandler);
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
