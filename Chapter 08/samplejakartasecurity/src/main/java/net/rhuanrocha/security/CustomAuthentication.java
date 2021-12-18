package net.rhuanrocha.security;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.AuthenticationException;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.authentication.mechanism.http.HttpAuthenticationMechanism;
import jakarta.security.enterprise.authentication.mechanism.http.HttpMessageContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.LinkedHashSet;
import java.util.Set;

//@ApplicationScoped
public class CustomAuthentication {//implements HttpAuthenticationMechanism {

    //@Override
    public AuthenticationStatus validateRequest(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, HttpMessageContext httpMessageContext) throws AuthenticationException {
        String username = httpServletRequest.getParameter("username");
        String password = httpServletRequest.getParameter("password");

        if("admin".equals(username) && "admin".equals(password)){
            Set<String> roles = new LinkedHashSet<>();
            roles.add("admin");
            return httpMessageContext.notifyContainerAboutLogin(username, roles);
        }
        return httpMessageContext.responseUnauthorized();
    }
}
