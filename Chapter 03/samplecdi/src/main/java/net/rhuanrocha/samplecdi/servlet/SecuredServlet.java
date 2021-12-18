package net.rhuanrocha.samplecdi.servlet;

import net.rhuanrocha.samplecdi.beans.SecuredBean;
import net.rhuanrocha.samplecdi.exceptions.AuthenticationException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/secured")
public class SecuredServlet extends HttpServlet {

    @Inject
    private SecuredBean securedBean;

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        try {
            resp.getWriter().println(securedBean.generateText(username));
        } catch (AuthenticationException e) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            e.printStackTrace();
        }

    }
}
