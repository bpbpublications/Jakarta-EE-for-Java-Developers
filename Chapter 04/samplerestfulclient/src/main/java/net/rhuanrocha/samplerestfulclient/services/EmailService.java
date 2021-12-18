package net.rhuanrocha.samplerestfulclient.services;

import java.util.logging.Logger;

public class EmailService {

    private static Logger logger = Logger.getLogger(EmailService.class.getName());

    public Boolean send(String from, String to, String subject, String message){
        //Logic to send email.
        logger.info(String.format("EMAIL: from %s to %s with a subject %s and message %s",from,to,subject,message));
        return true;
    }
}
