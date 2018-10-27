package pl.studia.bank.model;

import lombok.Data;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BankAccount {
    private BigDecimal balance;
    private UUID id;
    private UUID ownerId;
    private DateTime createdAt;

}
