package pl.studia.bank.service;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.exception.BankAccountException;
import pl.studia.bank.exception.BankCreditException;
import pl.studia.bank.exception.BankDepositException;
import pl.studia.bank.model.Kredyt;
import pl.studia.bank.model.Lokata;
import pl.studia.bank.model.OperationResult;
import pl.studia.bank.model.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
public class BankService {
    @Autowired
    private BankDAO bankDAO;

    public OperationResult addBankAccount() {

        OperationResult result = new OperationResult();

        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setId(UUID.randomUUID());
        newBankAccount.setOwnerId(UUID.randomUUID());
        newBankAccount.setCreatedAt(DateTime.now());

        try {
            bankDAO.addBankAccount(newBankAccount);
            result.setFinished(true);
        }catch (BankAccountException ex){
            result.setFinished(false);
            log.error(ex.getMessage());
        }

        return result;
    }

    public BankAccount deleteBankAccount(int id){

        return bankDAO.deleteBankAccount(id);
    }

    //TODO docelowo int duration, BigDecimal value <-- przekazać w argumentach
    public OperationResult addBankDeposit() {

        OperationResult result = new OperationResult();

        Lokata lokata = new Lokata();
        lokata.setId(UUID.randomUUID());
        lokata.setBillingPeriod((int)Math.random());
        lokata.setValue(new BigDecimal((int)Math.random()));

        try {
            bankDAO.addBankDeposit(lokata);
            result.setFinished(true);
        }catch (BankDepositException ex){
            result.setFinished(false);
            log.error(ex.getMessage());
        }

        return result;
    }

    public OperationResult addBankCredit(){
        OperationResult result = new OperationResult();

        Kredyt kredyt = new Kredyt();
        kredyt.setId(UUID.randomUUID());
        kredyt.setVaule(new BigDecimal((int)Math.random()));
        kredyt.setBillingPeriod((int)Math.random());
        kredyt.setCreditInterestRate(Math.random());

        try {
            bankDAO.addBankCredit(kredyt);
            result.setFinished(true);
        } catch (BankCreditException ex) {
            result.setFinished(false);
            log.error(ex.getMessage());
        }

        return result;
    }
}
