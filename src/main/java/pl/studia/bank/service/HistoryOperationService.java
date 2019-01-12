package pl.studia.bank.service;


import org.springframework.stereotype.Service;
import pl.studia.bank.dao.HistoryDAO;
import pl.studia.bank.model.BankOperation;

import java.util.List;

@Service
public class HistoryOperationService {

    private HistoryDAO historyDAO;

    HistoryOperationService( HistoryDAO historyDAO) {
        this.historyDAO = historyDAO;
    }

    public void  addBankOperation(BankOperation newBankOperation){
        historyDAO.addBankOperation(newBankOperation);
    }

    public BankOperation findBankOperation(String id){
        return historyDAO.findBankOperation(id);
    }

    public List<BankOperation> getAllBankOperation(){
        return historyDAO.getAllBankOperation();
    }


}
