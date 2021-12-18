package net.rhuanrocha.schedule;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;
import java.util.logging.Logger;

@Singleton
@Startup
public class HelloProgrammaticallySchedule {

    private Logger logger = Logger.getLogger(HelloProgrammaticallySchedule.class.getName());

    @Resource
    private TimerService timerService;

    @PostConstruct
    private void init(){
        ScheduleExpression expression = new ScheduleExpression();
        expression.hour("*");
        expression.minute("0,10,20,30,40,50");

        timerService.createCalendarTimer(expression);
    }

    @Timeout
    public void execute(Timer timer) {
        logger.info("Hello programmatically :)");
    }

}
