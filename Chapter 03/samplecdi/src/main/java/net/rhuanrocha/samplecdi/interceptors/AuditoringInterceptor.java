package net.rhuanrocha.samplecdi.interceptors;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Logger;

import static java.lang.String.*;

@Auditoring
@Interceptor
@Priority(100)
public class AuditoringInterceptor implements Serializable {

    private final Logger logger = Logger.getLogger(AuditoringInterceptor.class.getName());

    private final String PATTERN_AUDIT = "username:%s ; class called:%s ; method called:%s";

    @Inject
    private HttpServletRequest request;

    @AroundInvoke
    public Object intercept(InvocationContext invocationContext) throws Exception {

        String username = (String) request.getSession().getAttribute("username");
        String className = invocationContext.getTarget().getClass().getName();
        String methodName = invocationContext.getMethod().getName();

        if(username == null){
            logger.info(format(PATTERN_AUDIT,"anonymous",className,methodName));

        }
        else{
            logger.info(format(PATTERN_AUDIT,username,className,methodName));
        }

        return invocationContext.proceed();
    }
}
