package net.rhuanrocha.samplecdi.servlet;

import net.rhuanrocha.samplecdi.beans.Email;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/email")
public class EmailServlet extends HttpServlet {

    private static Logger logger = Logger.getLogger(EmailServlet.class.getName());

    @Inject
    private Event<Email> emailEvent;

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String from = req.getParameter("from");
        String to = req.getParameter("to");

        if( from == null || from.isEmpty()
                || to == null || from.isEmpty()){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        String subject = req.getParameter("subject");
        String message = req.getParameter("from");
        String async = req.getParameter("async");



        if("true".equals(async)){
            emailEvent.fireAsync(Email.of(from, to, subject, message));
            logger.info("Async event sent");
        }
        else{
            emailEvent.fire(Email.of(from, to, subject, message));
            logger.info("Sync event sent");
        }


    }
}
