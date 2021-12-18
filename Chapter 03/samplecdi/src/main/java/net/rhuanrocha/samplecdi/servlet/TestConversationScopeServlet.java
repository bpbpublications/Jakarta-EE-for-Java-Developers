package net.rhuanrocha.samplecdi.servlet;

import net.rhuanrocha.samplecdi.beans.ShoppingCart;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/testconversationscope")
public class TestConversationScopeServlet extends HttpServlet {

    @Inject
    private ShoppingCart shoppingCart;

    private final String templateHtml = "<html><body><span>%d</span></body></html>";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp
                .getWriter()
                .println(String.format(templateHtml, shoppingCart.getItemNumber()));

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if("startConversation".equalsIgnoreCase(action)){
            shoppingCart.startConversation();
        }
        else if("endConversation".equalsIgnoreCase(action)){
            shoppingCart.endConversation();
        }
        else{
            shoppingCart.increaseItemNumber();
        }

        String cid = (Objects.isNull(shoppingCart.getConversationId()) ? "" : shoppingCart.getConversationId());
        resp.addHeader("cid",cid);
    }


}
