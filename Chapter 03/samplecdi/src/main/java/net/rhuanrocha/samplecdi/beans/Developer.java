package net.rhuanrocha.samplecdi.beans;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.util.logging.Logger;

public class Developer extends Person{

    private static Logger  LOGGER = Logger.getLogger(Developer.class.getName());

    @Inject
    private Brain brain;

    @PostConstruct
    public void init(){
        LOGGER.info("Executed.");
    }


    @PreDestroy
    public void prepareToDestroy(){
        LOGGER.info("Preparing to destroy processing...");
    }

    public void solveProblem(Object problem){

        //Logic before reasoning
        Object solve = brain.reason(problem);
        //Logic after reasoning
    }
}
