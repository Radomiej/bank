package pl.studia.bank.dao;

import org.springframework.stereotype.Component;
import pl.studia.bank.exception.BankAccountException;
import pl.studia.bank.exception.BankCreditException;
import pl.studia.bank.exception.BankDepositException;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.Credit;
import pl.studia.bank.model.Deposit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BankDAO {
    List<BankAccount> bankAccounts = new ArrayList<>();
    List<Deposit> bankDeposits = new ArrayList<>();
    List<Credit> bankCredits = new ArrayList<>();

    public void addBankAccount(BankAccount newBankAccount) throws BankAccountException {
        bankAccounts.add(newBankAccount);
    }

    public void addBankDeposit(Deposit newBankDeposit) throws BankDepositException { bankDeposits.add(newBankDeposit); }

    public void addBankCredit(Credit newBankCredit) throws BankCreditException { bankCredits.add(newBankCredit); }

    public BankAccount deleteBankAccount(String id) {
        BankAccount bankAccount = findBankAccount(id);
        boolean remove = bankAccounts.remove(bankAccount);
        return remove ? bankAccount : null;
    }

    public BankAccount findBankAccount(String id) {
        final UUID uuid = UUID.fromString(id);
        List<BankAccount> collected = bankAccounts.stream().filter(a -> a.getId().equals(uuid)).collect(Collectors.toList());
        return collected.isEmpty() ? null : collected.get(0);
    }

    public List<Deposit> findDepositsByActive(final boolean active, final int currentTime){
        List<Deposit> results = new ArrayList<>();
        for(Deposit deposit : bankDeposits){
            boolean depositIsActive = deposit.getCreateTime() <= currentTime && deposit.getEndTime() >= currentTime;
            if(depositIsActive && active) results.add(deposit);
        }
        return results;
    }

    public List<Credit> findCreditsByActive(final boolean active, final int currentTime) {
        List<Credit> results = new ArrayList<>();
        for(Credit credit : bankCredits){
            boolean depositIsActive = credit.getCreateTime() <= currentTime && credit.getEndTime() >= currentTime;
            if(depositIsActive && active) results.add(credit);
        }
        return results;
    }
}
