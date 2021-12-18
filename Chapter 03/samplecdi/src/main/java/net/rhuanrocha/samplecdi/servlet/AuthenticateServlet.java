package net.rhuanrocha.samplecdi.servlet;

import net.rhuanrocha.samplecdi.authentication.AuthenticationDatasource;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/authenticate")
public class AuthenticateServlet extends HttpServlet {

    @Inject
    private AuthenticationDatasource authenticationDatasource;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username == null || password == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if(authenticationDatasource.validate(username,password)){
            req.getSession().setAttribute("username",username);
        }
        else{
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        }


    }
}
