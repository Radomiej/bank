package pl.studia.bank.dao;

import org.springframework.stereotype.Component;
import pl.studia.bank.model.BankAccount;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankDAO {
    List<BankAccount> bankAccounts = new ArrayList<>();

    public void addBankAccount(BankAccount newBankAccount) {
        bankAccounts.add(newBankAccount);
    }
}
