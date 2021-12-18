package net.rhuanrocha.service;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.*;
import net.rhuanrocha.bean.Email;


@Stateless
public class EmailServiceTopic {

    @Inject
    @JMSConnectionFactory( "java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/topic/EmailTopic")
    private Topic topic;

    public void sendEmail(Email email){
        JMSProducer producer = context.createProducer();
        producer.send(topic,email);
    }

    public void sendEmailAsMap(Email email) throws JMSException {
        JMSProducer producer = context.createProducer();
        MapMessage mapMessage = context.createMapMessage();
        mapMessage.setString("subject", email.getSubject());
        mapMessage.setString("to", email.getTo());
        mapMessage.setString("message", email.getMessage());

        producer.send(topic,mapMessage);
    }
}
