package pl.studia.bank.helper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public enum BigDecimalFactory {
    INSTANCE;

    private MathContext mathContext;
    private BigDecimal zero, one;
    BigDecimalFactory(){
        this.mathContext = new MathContext(6, RoundingMode.HALF_UP);
        zero = produceFromInt(0);
        one = produceFromInt(1);
    }

    public MathContext getMathContext(){
        return mathContext;
    }

    public BigDecimal produceFromInt(int var){
        return new BigDecimal(var,mathContext);
    }

    public BigDecimal produceFromDouble(double var) {
        return new BigDecimal(var, mathContext);
    }

    public BigDecimal getZeroValue() {
        return zero;
    }

    public BigDecimal produceFromLong(long var) {
        return new BigDecimal(var, mathContext);
    }

    public BigDecimal copy(BigDecimal bigDecimal){
        BigDecimal copy = bigDecimal;
        return copy;
    }

    public BigDecimal getOneValue() {
        return one;
    }
}
