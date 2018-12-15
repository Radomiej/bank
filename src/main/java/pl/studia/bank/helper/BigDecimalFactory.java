package pl.studia.bank.helper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public enum BigDecimalFactory {
    INSTANCE;

    private MathContext mathContext;

    BigDecimalFactory(){
        this.mathContext = new MathContext(6, RoundingMode.HALF_UP);
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
        return produceFromInt(0);
    }

    public BigDecimal produceFromLong(long var) {
        return new BigDecimal(var, mathContext);
    }
}
