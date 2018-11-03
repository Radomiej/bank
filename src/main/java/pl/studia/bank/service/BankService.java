package pl.studia.bank.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.model.Lokata;
import pl.studia.bank.model.OperationResult;
import pl.studia.bank.model.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

@Service
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
        }catch (Exception ex){
            result.setFinished(false);
        }

        return result;
    }

    //TODO docelowo int duration, BigDecimal value <-- przekazać w argumentach
    public OperationResult addBankDeposit() {

        OperationResult result = new OperationResult();

        Lokata lokata = new Lokata();
        lokata.setDuration((int)Math.random());
        lokata.setValue(new BigDecimal((int)Math.random()));

        try {
            bankDAO.addBankDeposit(lokata);
            result.setFinished(true);
        }catch (Exception ex){
            result.setFinished(false);
        }

        return result;
    }
}
