package pl.studia.bank.service;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.model.OperationResult;
import pl.studia.bank.model.BankAccount;

import java.util.UUID;

@Service
public class BankService {
    @Autowired
    private BankDAO bankDAO;

    public OperationResult addBankAccount() {

        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setId(UUID.randomUUID());
        newBankAccount.setOwnerId(UUID.randomUUID());
        newBankAccount.setCreatedAt(DateTime.now());

        bankDAO.addBankAccount(newBankAccount);

        OperationResult result = new OperationResult();
        return result;
    }
}
