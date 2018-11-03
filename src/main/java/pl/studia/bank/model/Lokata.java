package pl.studia.bank.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Lokata {
    private BigDecimal value;
    private int duration;
}
