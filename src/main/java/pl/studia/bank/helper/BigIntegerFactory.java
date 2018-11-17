package pl.studia.bank.helper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public enum BigIntegerFactory {
    INSTANCE;

    private MathContext mathContext;

    private BigIntegerFactory(){
        this.mathContext = new MathContext(6, RoundingMode.HALF_UP);
    }

    public BigDecimal produceFromInt(int var){
        return new BigDecimal(var,mathContext);
    }
}
