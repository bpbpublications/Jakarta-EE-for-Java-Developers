package net.rhuanrocha.mdb;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.MessageListener;
import java.util.logging.Logger;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:/jms/queue/MyQueue"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue")
})
public class HelloMDB  implements MessageListener {

    private Logger logger = Logger.getLogger(HelloMDB.class.getName());

    @Resource
    private MessageDrivenContext mdc;

    @Override
    public void onMessage(javax.jms.Message message) {
        logger.info(message.toString());
        //business logic
    }
}
