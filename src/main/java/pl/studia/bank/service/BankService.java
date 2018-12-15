package pl.studia.bank.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.exception.BankAccountException;
import pl.studia.bank.exception.BankCreditException;
import pl.studia.bank.exception.BankDepositException;
import pl.studia.bank.helper.BigDecimalFactory;
import pl.studia.bank.model.*;

import java.util.UUID;

@Service
@Slf4j
public class BankService {
    @Autowired
    private BankDAO bankDAO;

    @Autowired
    private TimeService timeService;

    public OperationResult<BankAccount> addBankAccount() {

        OperationResult<BankAccount> result = new OperationResult();

        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setId(UUID.randomUUID());
        newBankAccount.setOwnerId(UUID.randomUUID());
        newBankAccount.setCreatedAt(timeService.getCurrentTime());
        newBankAccount.setBalance(BigDecimalFactory.INSTANCE.produceFromInt(0));

        result.setData(newBankAccount);
        result.setSuccess(true);

        try {
            bankDAO.addBankAccount(newBankAccount);
        }catch (BankAccountException ex){
            result.setSuccess(false);
            log.error(ex.getMessage());
        }
        return result;
    }

    public OperationResult<BankAccount> deleteBankAccount(String id){

        BankAccount bankAccount = bankDAO.deleteBankAccount(id);

        OperationResult<BankAccount> bankAccountOperationResult = new OperationResult<>();
        bankAccountOperationResult.setSuccess(bankAccount!=null);
        bankAccountOperationResult.setData(bankAccount);
        return bankAccountOperationResult;
    }

    //TODO docelowo int duration, BigDecimal value <-- przekazać w argumentach
    public OperationResult<Deposit> addBankDeposit(Deposit deposit) {
        OperationResult<Deposit> result = new OperationResult();

        result.setData(deposit);
        result.setSuccess(true);

        try {
            bankDAO.addBankDeposit(deposit);
        }catch (BankDepositException ex){
            result.setSuccess(false);
            log.error(ex.getMessage());
        }

        return result;
    }

    public OperationResult<Credit> addBankCredit(Credit credit){
        OperationResult<Credit> result = new OperationResult();

        credit.setId(UUID.randomUUID());
        result.setData(credit);
        result.setSuccess(true);

        try {
            bankDAO.addBankCredit(credit);
        } catch (BankCreditException ex) {
            result.setSuccess(false);
            log.error(ex.getMessage());
        }

        return result;
    }

    public OperationResult<BankAccount> addDebitToBankAccount(String bankAccountId, Debit debit){
        OperationResult<BankAccount> result = new OperationResult();
        BankAccount bankAccount = bankDAO.findBankAccount(bankAccountId);
        bankAccount.setDebit(debit);

        result.setSuccess(true);
        result.setData(bankAccount);
        return result;
    }
}
