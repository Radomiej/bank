package pl.studia.bank.dao;

import org.springframework.stereotype.Component;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.BankOperation;

import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryDAO {
    List<BankOperation> historyList = new ArrayList<>();

    public void addBankOperation (BankOperation newBankOperation) {
        historyList.add(newBankOperation);
    }

}
