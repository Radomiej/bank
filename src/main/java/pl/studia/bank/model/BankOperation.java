package pl.studia.bank.model;

import lombok.Data;

import java.util.Date;

@Data
public class BankOperation {
    private int id;
private  int realisationDate;
private String description;
}
