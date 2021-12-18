package net.rhuanrocha.mdb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import net.rhuanrocha.bean.Email;

import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/topic/EmailTopic"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName  = "subscriptionDurability",
                propertyValue = "Durable"),
        @ActivationConfigProperty(propertyName = "subscriptionName",
                propertyValue = "MySubscriptionMDB"),
        @ActivationConfigProperty(propertyName = "shareSubscriptions",
                propertyValue="true")

})
public class EmailMdbTopic implements MessageListener {

    private Logger logger = Logger.getLogger(EmailMdbTopic.class.getName());

    @Override
    public void onMessage(Message message) {
        logger.info("Trying to read message from MDB");
        try {
            Email email = message.getBody(Email.class);
            sendEmail(email);
        } catch (JMSException e) {
            logger.severe(e.getMessage());
        }


    }

    private void sendEmail(Email email){
        //Logic to send email
        logger.info("to: "+email.getTo());
        logger.info("subject: "+email.getSubject());
        logger.info("message: "+email.getMessage());
    }
}
