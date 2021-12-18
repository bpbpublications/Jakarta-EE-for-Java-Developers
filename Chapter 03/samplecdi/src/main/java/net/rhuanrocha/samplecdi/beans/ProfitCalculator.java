package net.rhuanrocha.samplecdi.beans;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.math.RoundingMode;

@ApplicationScoped
public class ProfitCalculator implements Calculator {

    public BigDecimal calculate(BigDecimal income, BigDecimal spent){

        return income.subtract(spent).setScale(2, RoundingMode.HALF_UP);
    }
}
