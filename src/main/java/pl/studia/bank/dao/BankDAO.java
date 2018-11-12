package pl.studia.bank.dao;

import org.springframework.stereotype.Component;
import pl.studia.bank.exception.BankAccountException;
import pl.studia.bank.exception.BankCreditException;
import pl.studia.bank.exception.BankDepositException;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.Kredyt;
import pl.studia.bank.model.Lokata;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BankDAO {
    List<BankAccount> bankAccounts = new ArrayList<>();
    List<Lokata> bankDeposits = new ArrayList<>();
    List<Kredyt> bankCredits = new ArrayList<>();

    public void addBankAccount(BankAccount newBankAccount) throws BankAccountException {
        bankAccounts.add(newBankAccount);
    }

    public void addBankDeposit(Lokata newBankDeposit) throws BankDepositException { bankDeposits.add(newBankDeposit); }

    public void addBankCredit(Kredyt newBankCredit) throws BankCreditException { bankCredits.add(newBankCredit); }

    public BankAccount deleteBankAccount(int id) {
        List<BankAccount> collected = bankAccounts.stream().filter(a -> a.getId() == UUID.randomUUID()).collect(Collectors.toList());
        return collected.isEmpty() ? null : collected.get(0);
    } //TODO zaszyty random UUID

}
