package pl.studia.bank.dao;

import org.springframework.stereotype.Component;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.BankOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class HistoryDAO {
    private List<BankOperation> historyList = new ArrayList<>();

    public void addBankOperation (BankOperation newBankOperation) {
        historyList.add(newBankOperation);
    }

    public BankOperation findBankOperation(String id) {
        final UUID uuid = UUID.fromString(id);
        List<BankOperation> collected = historyList.stream().filter(a -> a.getId().equals(uuid)).collect(Collectors.toList());
        return collected.isEmpty() ? null : collected.get(0);
    }

    public List<BankOperation> getAllBankOperation() {
        return historyList;
    }


}
