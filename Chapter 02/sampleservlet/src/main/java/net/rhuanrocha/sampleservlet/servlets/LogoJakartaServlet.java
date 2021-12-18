package net.rhuanrocha.sampleservlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.PushBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

@WebServlet(name = "LogoJakarta", value ="/logojakarta" )
public class LogoJakartaServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PushBuilder pushBuilder = req.newPushBuilder();
        //if HTTP is disabled the PushBuilder is returned null.
        if (Objects.nonNull(pushBuilder)) {
            pushBuilder

                .path("images/jakarta_ee_logo.jpg")
                .addHeader("content-type", "image/jpg")
                .push();
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.println("<html>");
        printWriter.println("<body>");
        printWriter.println("<img src='images/jakarta_ee_logo.jpg'>");
        printWriter.println("</body>");
        printWriter.println("</html>");

    }
}
