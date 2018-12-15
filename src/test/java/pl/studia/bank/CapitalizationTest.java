package pl.studia.bank;

import org.junit.Assert;
import org.junit.Test;
import pl.studia.bank.helper.BigIntegerFactory;
import pl.studia.bank.helper.Capitalization;

import java.math.BigDecimal;

public class CapitalizationTest {

    @Test
    public void testCapitalization(){
        BigDecimal startValue = BigIntegerFactory.INSTANCE.produceFromInt(1000);
        BigDecimal interestValue = BigIntegerFactory.INSTANCE.produceFromDouble(0.1d);
        BigDecimal endValue = Capitalization.calculate(startValue, interestValue, 1);

        Assert.assertTrue(BigIntegerFactory.INSTANCE.produceFromDouble(1100d).compareTo(endValue) == 0);
        System.out.println("End value: " + endValue);
    }
}
