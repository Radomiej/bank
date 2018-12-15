package pl.studia.bank.helper;


import java.math.BigDecimal;

public class Capitalization {
    public static BigDecimal calculate(BigDecimal value, BigDecimal creditInterestRate, int interestRateAmount) {
        BigDecimal result = value;

        for (int i = 0; i < interestRateAmount; i++) {
            result = result.add(result.multiply(creditInterestRate));
        }
        return result;
    }
}