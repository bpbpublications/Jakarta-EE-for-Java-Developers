package net.rhuanrocha.samplecdi.interceptors;


import net.rhuanrocha.samplecdi.exceptions.AuthenticationException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

@Interceptor
@Authentication
@Priority(200)
public class AuthenticationInterceptor implements Serializable {

    @Inject
    private HttpServletRequest request;

    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws Exception {

        String username = (String) request.getSession().getAttribute("username");

        if (username == null){
            throw new AuthenticationException("User is not authenticated.");
        }

        return invocationContext.proceed();
    }
}
