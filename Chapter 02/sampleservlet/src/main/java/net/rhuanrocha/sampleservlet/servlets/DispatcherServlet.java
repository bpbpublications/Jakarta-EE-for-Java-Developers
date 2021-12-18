package net.rhuanrocha.sampleservlet.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(value = "/helloworld.html")
public class DispatcherServlet extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String type = req.getParameter("type");

        HttpSession session = req.getSession(true);

        session.getAttribute("user");

        PrintWriter writer = resp.getWriter();
        writer.println("<html>");
        writer.println("<heard>");
        writer.println("</heard>");
        writer.println("<body>");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/helloworld");
        if("forward".equals(type)){
            dispatcher.forward(req, resp);
        }else {
            dispatcher.include(req, resp);
        }
        writer.println("</body>");
        writer.println("</html>");
    }


}
