package pl.studia.bank.helper;

import java.math.BigDecimal;

public enum BigDecimalOperation {
    INSTANCE;

    public BigDecimal add(BigDecimal valueA, BigDecimal valueB){
        BigDecimal result = valueA.add(valueB, BigDecimalFactory.INSTANCE.getMathContext());
        return result;
    }

    public BigDecimal remove(BigDecimal valueA, BigDecimal valueB) {
        BigDecimal result = valueA.subtract(valueB, BigDecimalFactory.INSTANCE.getMathContext());
        return result;
    }

    public BigDecimal multiply(BigDecimal valueA, BigDecimal valueB) {
        BigDecimal result = valueA.multiply(valueB, BigDecimalFactory.INSTANCE.getMathContext());
        return result;
    }

    public BigDecimal divide(BigDecimal valueA, BigDecimal valueB) {
        BigDecimal result = valueA.divide(valueB, BigDecimalFactory.INSTANCE.getMathContext());
        return result;
    }
}
