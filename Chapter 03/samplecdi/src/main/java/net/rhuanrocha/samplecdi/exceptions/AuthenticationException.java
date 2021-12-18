package net.rhuanrocha.samplecdi.exceptions;

import net.rhuanrocha.samplecdi.interceptors.Authentication;

public class AuthenticationException extends Exception {

    public AuthenticationException(String message) {
        super(message);
    }

}
