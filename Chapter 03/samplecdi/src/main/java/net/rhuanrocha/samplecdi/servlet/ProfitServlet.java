package net.rhuanrocha.samplecdi.servlet;

import net.rhuanrocha.samplecdi.beans.Calculator;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet("/profit")
public class ProfitServlet extends HttpServlet {

    @Inject
    private Calculator calculator;

    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String income = req.getParameter("income");
        String spent = req.getParameter("spent");

        if(income == null || income.isEmpty()
                || spent == null || spent.isEmpty()){

            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        BigDecimal profit = calculator.calculate(
                new BigDecimal(Double.parseDouble(income)).setScale(2, RoundingMode.HALF_UP),
                new BigDecimal(Double.parseDouble(spent)).setScale(2, RoundingMode.HALF_UP));

        resp.getWriter().println("Profit: "+ profit.toString());
    }
}
