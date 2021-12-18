package net.rhuanrocha.samplecdi.event;

import net.rhuanrocha.samplecdi.beans.Email;

import javax.enterprise.event.Observes;
import javax.enterprise.event.ObservesAsync;
import java.util.logging.Logger;

public class EmailObserver {

    private static Logger logger = Logger.getLogger(EmailObserver.class.getName());

    public void send(@Observes Email email){
        this.sendEmail(email);
    }

    public void sendAsync(@ObservesAsync Email email){
        this.sendEmail(email);
    }

    private void sendEmail(Email email){
        //Logic to send email.
        logger.info("Email "+email.getSubject().orElse("")
                + " sent from "+ email.getFrom()
                + " to "+ email.getTo());
    }

}
