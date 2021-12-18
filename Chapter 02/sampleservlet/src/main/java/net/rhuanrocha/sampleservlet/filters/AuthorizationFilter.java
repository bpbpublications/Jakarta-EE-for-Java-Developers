package net.rhuanrocha.sampleservlet.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@WebFilter(filterName = "AuthorizationFilter", urlPatterns = "/security/*")
public class AuthorizationFilter implements Filter {

    private Map<String, String> passwords;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        passwords = new HashMap<>();
        passwords.put("admin", "admin");
        passwords.put("developer", "dev123");

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(!validateAuth((HttpServletRequest)servletRequest,(HttpServletResponse) servletResponse)) {
            ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorided access request");
        }
        else{
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    private boolean validateAuth(HttpServletRequest request, HttpServletResponse response){

        String login = request.getHeader("login");

        if (Objects.isNull(login) || !passwords.containsKey(login) ){
            return false;
        }

        String password = request.getHeader("password");

        if (Objects.isNull(password) || !passwords.get(login).equals(password) ){
            return false;
        }

        return true;

    }
}
