package pl.studia.bank.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BasicTimeSaldo {
    private UUID id;
    private BigDecimal value;
    private int createTime;
    private int endTime;
    private Interest interest; //Dla mniej kumatych po prostu oprocentowanie :3
    private boolean exhausted;
    private String ownerBankAccount;
}
