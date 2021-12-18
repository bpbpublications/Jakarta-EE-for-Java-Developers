package net.rhuanrocha.consumer;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.Topic;
import net.rhuanrocha.bean.Email;

import java.util.logging.Logger;

@Stateless
public class EmailConsumerTopic {

    private Logger logger = Logger.getLogger(EmailConsumer.class.getName());

    @Inject
    @JMSConnectionFactory( "java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/topic/EmailTopic")
    private Topic topic;


    public void consumeMessageNondurableUnshared(){
        JMSConsumer consumer = context.createConsumer(topic);
        logger.info("Trying to read message");
        Email email = consumer.receiveBody(Email.class);
        sendEmail(email);

    }

    public void consumeMessageDurableUnshared(){
        JMSConsumer consumer = context.createDurableConsumer(topic,"Mysubscription1");
        logger.info("Trying to read message");
        Email email = consumer.receiveBody(Email.class);
        sendEmail(email);

    }

    public void consumeMessageDurableShared(){
        JMSConsumer consumer1 = context.createSharedDurableConsumer(topic,"Mysubscription2");
        JMSConsumer consumer2 = context.createSharedDurableConsumer(topic,"Mysubscription2");

        logger.info("Trying to read message from consumer1");
        Email email1 = consumer1.receiveBody(Email.class);
        sendEmail(email1);

        logger.info("Trying to read message from consumer2");
        Email email2 = consumer2.receiveBody(Email.class);
        sendEmail(email2);

    }

    public void consumeMessageNondurableShared(){
        JMSConsumer consumer1 = context.createSharedConsumer(topic,"Mysubscription2");
        JMSConsumer consumer2 = context.createSharedConsumer(topic,"Mysubscription2");

        logger.info("Trying to read message from consumer1");
        Email email1 = consumer1.receiveBody(Email.class);
        sendEmail(email1);

        logger.info("Trying to read message from consumer2");
        Email email2 = consumer2.receiveBody(Email.class);
        sendEmail(email2);

    }

    private void sendEmail(Email email){
        //Logic to send email
        logger.info("to: "+email.getTo());
        logger.info("subject: "+email.getSubject());
        logger.info("message: "+email.getMessage());
    }
}
