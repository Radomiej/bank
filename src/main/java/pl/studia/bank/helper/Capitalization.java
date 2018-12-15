package pl.studia.bank.helper;


import java.math.BigDecimal;

public class Capitalization {

    public BigDecimal calculate(BigDecimal value, double creditInterestRate, int InterestRateAmount){
        BigDecimal result = value ;

        for(int i = 0; i < InterestRateAmount; i ++ ) {
            result = result.add(result.multiply(new BigDecimal(creditInterestRate) ));
        }
        return result;
        }
}