package pl.studia.bank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Lokata {
    private UUID id;
    private BigDecimal value;
    private int billingPeriod; //okres naliczania opłat
}
