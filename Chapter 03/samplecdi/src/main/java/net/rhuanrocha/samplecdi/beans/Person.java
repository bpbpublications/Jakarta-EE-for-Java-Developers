package net.rhuanrocha.samplecdi.beans;

import javax.annotation.PostConstruct;
import java.util.logging.Logger;

public class Person {

    private static Logger LOGGER = Logger.getLogger(Person.class.getName());

    private String name;

    @PostConstruct
    public void init(){
        LOGGER.info("Executed.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
