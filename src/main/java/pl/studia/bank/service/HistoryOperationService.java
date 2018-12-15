package pl.studia.bank.service;

import org.springframework.stereotype.Service;
import pl.studia.bank.dao.HistoryDAO;
import pl.studia.bank.model.BankOperation;

@Service
public class HistoryOperationService {

    private HistoryDAO historyDAO;

    HistoryOperationService( HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    public void  addOperation(BankOperation newBankOperation){
        historyDAO.addBankOperation(newBankOperation);
    }
}
