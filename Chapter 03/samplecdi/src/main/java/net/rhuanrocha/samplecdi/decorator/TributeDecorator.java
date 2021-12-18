package net.rhuanrocha.samplecdi.decorator;

import net.rhuanrocha.samplecdi.beans.Calculator;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Decorator
public class TributeDecorator implements Calculator{

    @Inject
    @Delegate
    private Calculator calculator;


    @Override
    public BigDecimal calculate(BigDecimal income, BigDecimal spent) {
        BigDecimal newIncome = income.multiply(new BigDecimal(0.9).setScale(2, RoundingMode.HALF_UP));
        return calculator.calculate(newIncome, spent);
    }
}
