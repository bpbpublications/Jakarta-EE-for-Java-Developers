package net.rhuanrocha.samplecdi.servlet;


import net.rhuanrocha.samplecdi.beans.MessageWriter;
import net.rhuanrocha.samplecdi.qualifiers.MessageField;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("messages")
public class MessageServlet extends HttpServlet {

    @Inject
    @MessageField
    private MessageWriter messageWriter;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");
        if(message != null && !message.isEmpty()){
            messageWriter.add(message);
        }

        resp.getWriter().println("Message added: "+message);

    }
}
