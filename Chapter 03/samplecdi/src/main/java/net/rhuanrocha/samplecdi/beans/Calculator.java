package net.rhuanrocha.samplecdi.beans;

import java.math.BigDecimal;

public interface Calculator {

    public BigDecimal calculate(BigDecimal income, BigDecimal spent);
}
