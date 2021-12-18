package net.rhuanrocha.schedule;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import java.util.logging.Logger;

@Singleton
public class HelloSchedule {

    private Logger logger = Logger.getLogger(HelloSchedule.class.getName());

    @Schedule(hour = "*",minute = "0,10,20,30,40,50")
    public void logHello(){
        logger.info("Hello :)");
    }
}
