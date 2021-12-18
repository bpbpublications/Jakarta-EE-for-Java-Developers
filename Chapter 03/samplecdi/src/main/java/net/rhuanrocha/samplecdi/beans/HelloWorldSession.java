package net.rhuanrocha.samplecdi.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;

@SessionScoped
public class HelloWorldSession implements Serializable {

    private String message;

    private LocalDateTime date;

    @PostConstruct
    public void init(){
        this.message = "Hello World";
        this.date = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
