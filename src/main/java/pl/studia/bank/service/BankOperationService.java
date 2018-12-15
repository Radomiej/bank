package pl.studia.bank.service;

import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.helper.BigIntegerOperation;
import pl.studia.bank.model.BankAccount;

import java.math.BigDecimal;

@Service
public class BankOperationService {

    private BankDAO bankDAO;

    BankOperationService(BankDAO bankDAO){

        this.bankDAO = bankDAO;
    }
    public void addToAccount(String accountId, BigDecimal addAmount){
        BankAccount bankAccount = bankDAO.findBankAccount(accountId);
        BigDecimal newBalance = BigIntegerOperation.INSTANCE.add(bankAccount.getBalance(), addAmount);
        bankAccount.setBalance(newBalance);
    }

    public void removeToAccount(String accountId, BigDecimal addAmount){
        BankAccount bankAccount = bankDAO.findBankAccount(accountId);
        BigDecimal newBalance = BigIntegerOperation.INSTANCE.remove(bankAccount.getBalance(), addAmount);
        bankAccount.setBalance(newBalance);
    }
}
