package pl.studia.bank.helper;

import java.math.BigDecimal;

public enum BigIntegerOperation {
    INSTANCE;

    public BigDecimal add(BigDecimal valueA, BigDecimal valueB){
        BigDecimal result = valueA.add(valueB);
        return result;
    }

    public BigDecimal remove(BigDecimal valueA, BigDecimal valueB) {
        BigDecimal result = valueA.subtract(valueB);
        return result;
    }
}
