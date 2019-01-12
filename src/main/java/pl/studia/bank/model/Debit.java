package pl.studia.bank.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Debit {
    private BigDecimal maxDebit;
    private BigDecimal value;
}
