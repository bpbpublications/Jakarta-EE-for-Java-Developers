package net.rhuanrocha.samplecdi.servlet;

import net.rhuanrocha.samplecdi.beans.HelloWorld;
import net.rhuanrocha.samplecdi.beans.HelloWorldSession;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet(value = "/test")
public class TestServlet extends HttpServlet {

    @Inject
    private HelloWorld helloWorld;

    @Inject
    private HelloWorldSession helloWorldSession;

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");

        if(Objects.isNull(type) || !type.equalsIgnoreCase("session")) {
            resp.getWriter().println(helloWorld.getDate() + " " + helloWorld.getMessage());
        }
        else{
            resp.getWriter().println(helloWorldSession.getDate() + " " + helloWorldSession.getMessage());
        }
    }
}
