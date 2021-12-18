package net.rhuanrocha.samplecdi.beans;

import net.rhuanrocha.samplecdi.exceptions.AuthenticationException;
import net.rhuanrocha.samplecdi.interceptors.Auditoring;
import net.rhuanrocha.samplecdi.interceptors.Authentication;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class SecuredBean {

    @Auditoring
    @Authentication
    public String generateText(String username) throws AuthenticationException {

        return "Wellcome "+username;
    }
}
