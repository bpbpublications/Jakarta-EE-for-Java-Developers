package net.rhuanrocha.consumer;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import jakarta.jms.JMSConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.Queue;
import net.rhuanrocha.bean.Email;



import java.util.logging.Logger;

@Stateless
public class EmailConsumer {

    private Logger logger = Logger.getLogger(EmailConsumer.class.getName());

    @Inject
    @JMSConnectionFactory( "java:jboss/DefaultJMSConnectionFactory")
    private JMSContext context;

    @Resource(mappedName = "java:/jms/queue/EmailQueue")
    private Queue queue;


    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void consumeMessage(){
        JMSConsumer consumer = context.createConsumer(queue);
        logger.info("Trying to read message");
        Email email = consumer.receiveBody(Email.class);
        sendEmail(email);

    }

    private void sendEmail(Email email){
        //Logic to send email
        logger.info("to: "+email.getTo());
        logger.info("subject: "+email.getSubject());
        logger.info("message: "+email.getMessage());
    }
}
