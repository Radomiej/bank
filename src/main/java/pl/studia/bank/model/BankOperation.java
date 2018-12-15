package pl.studia.bank.model;

import lombok.Data;


import java.util.UUID;

@Data
public class BankOperation {
    private UUID id;
private  int realisationDate;
private String description;
}
