package net.rhuanrocha.service;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.Queue;
import jakarta.jms.JMSException;
import jakarta.jms.MapMessage;

import net.rhuanrocha.bean.Email;

@Stateless
public class EmailService {

    @Inject
    @JMSConnectionFactory( "java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/EmailQueue")
    private Queue queue;

    public void sendEmail(Email email){
        JMSProducer producer = context.createProducer();
        producer.send(queue,email);
    }

    public void sendEmailAsMap(Email email) throws JMSException {
        JMSProducer producer = context.createProducer();
        MapMessage mapMessage = context.createMapMessage();
        mapMessage.setString("subject", email.getSubject());
        mapMessage.setString("to", email.getTo());
        mapMessage.setString("message", email.getMessage());

        producer.send(queue,mapMessage);
    }
}
