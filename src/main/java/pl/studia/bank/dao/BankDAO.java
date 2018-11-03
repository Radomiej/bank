package pl.studia.bank.dao;

import org.springframework.stereotype.Component;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.Lokata;

import java.util.ArrayList;
import java.util.List;

@Component
public class BankDAO {
    List<BankAccount> bankAccounts = new ArrayList<>();
    List<Lokata> bankDeposits = new ArrayList<>();

    public void addBankAccount(BankAccount newBankAccount) {
        bankAccounts.add(newBankAccount);
    }

    public void addBankDeposit(Lokata newBankDeposit) { bankDeposits.add(newBankDeposit); }
}
