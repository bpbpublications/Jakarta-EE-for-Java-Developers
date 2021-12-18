package net.rhuanrocha.sampleservlet.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@WebServlet(name = "DNS Servlet", urlPatterns = "/dns/*")
public class DNSServlet extends HttpServlet {

    // Map to store the IP mapped by domainName
    private ConcurrentMap<String, String> ipMap;

    public void init() throws ServletException {
        ipMap = new ConcurrentHashMap<>();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String domainName = req.getParameter("domainName");
        String ip = req.getParameter("ip");

        //In case of any value be null a 400 HTTP status is returned to client.
        if(Objects.isNull(domainName) || Objects.isNull(ip)){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        ipMap.put(domainName,ip);

        //A 201 HTTP status is returned to client
        resp.setStatus(HttpServletResponse.SC_CREATED);

    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String domainName = req.getParameter("domainName");

        //In case of domainName be null a 400 HTTP status is returned to client.
        if(Objects.isNull(domainName)){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        resp.setHeader("Content-Type","text/html; charset=UTF-8");

        PrintWriter printWriter = resp.getWriter();
        printWriter
                .println(Optional
                        .ofNullable(ipMap.get(domainName))
                        .orElse(""));


    }

}
