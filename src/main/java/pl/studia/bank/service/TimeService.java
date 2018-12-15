package pl.studia.bank.service;

import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.model.Credit;
import pl.studia.bank.model.Deposit;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TimeService {

    private AtomicInteger bankTime = new AtomicInteger();
    private BankService bankService;
    private BankDAO bankDAO;
    private BankOperationService bankOperationService;

    public TimeService(BankService bankService, BankDAO bankDAO, BankOperationService bankOperationService){
        this.bankService = bankService;
        this.bankDAO = bankDAO;
        this.bankOperationService = bankOperationService;
    }

    public synchronized void symulateNextTurn(){
        final int currentBankTime = bankTime.getAndIncrement();
        updateActiveDeposits(currentBankTime);
        updateActiveCredits(currentBankTime);

    }

    private void updateActiveDeposits(int currentBankTime) {
        List<Deposit> activeDeposits = bankDAO.findDepositsByActive(true, currentBankTime);
        for(Deposit deposit : activeDeposits){
            if(deposit.getEndTime() != currentBankTime) continue;

            BigDecimal balanceToAdd = deposit.getVaule();
            bankOperationService.addToAccount(deposit.getOwnerBankAccount(), balanceToAdd);
            deposit.setExhausted(true);
            System.out.println("Deposit has been ended: " + deposit);
        }
    }

    private void updateActiveCredits(int currentBankTime) {
        List<Credit> activeCredits = bankDAO.findCreditsByActive(true, currentBankTime);
        for(Credit credit : activeCredits){
            if(credit.getEndTime() != currentBankTime) continue;

            BigDecimal balanceToSubtract = credit.getVaule();
            bankOperationService.substractFromAccount(credit.getOwnerBankAccount(), balanceToSubtract);
            credit.setExhausted(true);
            System.out.println("Credit has been ended: " + credit);
        }
    }

    public int getCurrentTime() {
        return bankTime.get();
    }
}
