package pl.studia.bank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class Kredyt {
    private UUID id;
    private BigDecimal vaule;
    private double creditInterestRate; //Dla mniej kumatych po prostu oprocentowanie :3
    private int billingPeriod; //okres naliczania opłat

}
