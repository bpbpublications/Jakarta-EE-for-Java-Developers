package net.rhuanrocha.samplecdi.producers;


import net.rhuanrocha.samplecdi.beans.MessageWriter;
import net.rhuanrocha.samplecdi.qualifiers.Message;
import net.rhuanrocha.samplecdi.qualifiers.MessageField;

import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class MessageWriterProducer {

    private static Logger logger = Logger.getLogger(MessageWriterProducer.class.getName());
    private final String HOME_PATH = "/tmp/";
    private final String NAME_PATTERN = "MESSAGE_%d.txt";

    @Produces
    @MessageField
    MessageWriter messageWriter;

    public MessageWriterProducer(){
        String fileName = String.format(NAME_PATTERN, Timestamp.valueOf(LocalDateTime.now()).getTime());
        logger.info("Creating file message via producer field: "+HOME_PATH + fileName );
        this.messageWriter = new MessageWriter(HOME_PATH + fileName);
    }

    @Produces
    @Message
    public MessageWriter build (){

        String fileName = String.format(NAME_PATTERN, Timestamp.valueOf(LocalDateTime.now()).getTime());
        logger.info("Creating file message: "+HOME_PATH + fileName );
        return new MessageWriter(HOME_PATH + fileName);

    }


    public void clean(@Disposes @Message MessageWriter messageWriter){

        logger.info("Removing file message" );
        messageWriter.clean();

    }

}
