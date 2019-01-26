package pl.studia.bank.service;

import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.helper.BigDecimalFactory;
import pl.studia.bank.helper.BigDecimalOperation;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.Debit;

import java.math.BigDecimal;

@Service
public class BankOperationService {

    private BankDAO bankDAO;

    BankOperationService(BankDAO bankDAO){

        this.bankDAO = bankDAO;
    }
    public void addToAccount(String accountId, BigDecimal addAmount){
        BankAccount bankAccount = bankDAO.findBankAccount(accountId);

        addAmount = fillDebit(bankAccount, addAmount);
        fillAccountBalance(bankAccount, addAmount);

    }

    private void fillAccountBalance(BankAccount bankAccount, BigDecimal addAmount) {
        BigDecimal newBalance = BigDecimalOperation.INSTANCE.add(bankAccount.getBalance(), addAmount);
        bankAccount.setBalance(newBalance);
    }

    private BigDecimal fillDebit(BankAccount bankAccount, BigDecimal addAmount) {
        Debit debit = bankAccount.getDebit();
        if(debit == null) return addAmount;

        BigDecimal debitValue = debit.getValue();
        if(debitValue.compareTo(BigDecimalFactory.INSTANCE.getZeroValue()) == 0) return addAmount;

        BigDecimal resultDebitValue = debitValue.subtract(addAmount);
        if(resultDebitValue.compareTo(BigDecimalFactory.INSTANCE.getZeroValue()) >= 0) {
            debit.setValue(resultDebitValue);
            return BigDecimalFactory.INSTANCE.getZeroValue();
        }

        addAmount = debitValue.negate();
        debit.setValue(BigDecimalFactory.INSTANCE.getZeroValue());
        return addAmount;
    }

    public void subtractFromAccount(String accountId, BigDecimal addAmount){
        BankAccount bankAccount = bankDAO.findBankAccount(accountId);
        BigDecimal newBalance = BigDecimalOperation.INSTANCE.remove(bankAccount.getBalance(), addAmount);
        bankAccount.setBalance(newBalance);
    }
}
