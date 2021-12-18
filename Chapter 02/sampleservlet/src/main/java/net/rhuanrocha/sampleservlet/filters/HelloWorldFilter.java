package net.rhuanrocha.sampleservlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.Logger;

@WebFilter(filterName = "HelloWorldFilter", urlPatterns = "/test/*", asyncSupported = true)
public class HelloWorldFilter implements Filter {

    private static Logger logger = Logger.getLogger(HelloWorldFilter.class.getName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("Passed here.");
        filterChain.doFilter(servletRequest,servletResponse);

    }


}
