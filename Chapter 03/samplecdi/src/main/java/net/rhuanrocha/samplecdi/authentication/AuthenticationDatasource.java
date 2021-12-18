package net.rhuanrocha.samplecdi.authentication;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.*;

@ApplicationScoped
public class AuthenticationDatasource implements Serializable {

    private Map<String,String> userDatasource;

    @PostConstruct
    public void init(){
        userDatasource = new HashMap<>();
        userDatasource.put("rhuan","rhuan");
        userDatasource.put("admin","admin");
    }

    public boolean validate(String username, String password){

        if(username == null || password == null){
            return false;
        }

        if(Objects.equals(userDatasource.get(username),password)){
            return true;
        }

        return false;

    }



}
