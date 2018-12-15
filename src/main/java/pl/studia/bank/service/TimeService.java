package pl.studia.bank.service;

import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.model.Deposit;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TimeService {

    private AtomicInteger bankTime = new AtomicInteger();
    private BankService bankService;
    private BankDAO bankDAO;

    public TimeService(BankService bankService, BankDAO bankDAO){
        this.bankService = bankService;
        this.bankDAO = bankDAO;
    }

    public synchronized void symulateNextTurn(){
        final int currentBankTime = bankTime.getAndIncrement();
        List<Deposit> activeDeposits = bankDAO.findDepositsByActive(true, currentBankTime);
        for(Deposit deposit : activeDeposits){
            if(deposit.getEndTime() != currentBankTime) continue;

           // int endValue = capitalize(deposit.getValue(), deposit.)
            deposit.setExhausted(true);
        }

    }
}
