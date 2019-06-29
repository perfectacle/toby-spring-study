package example.domain;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

@Component("message")
public class MessageFactoryBean implements FactoryBean<Message> {
    @Override
    public Message getObject() {
        return Message.newMessage("Factory Bean");
    }

    @Override
    public Class<?> getObjectType() {
        return Message.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
