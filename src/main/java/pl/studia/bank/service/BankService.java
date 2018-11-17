package pl.studia.bank.service;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.exception.BankAccountException;
import pl.studia.bank.exception.BankCreditException;
import pl.studia.bank.exception.BankDepositException;
import pl.studia.bank.helper.BigIntegerFactory;
import pl.studia.bank.model.Credit;
import pl.studia.bank.model.Deposit;
import pl.studia.bank.model.OperationResult;
import pl.studia.bank.model.BankAccount;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Slf4j
public class BankService {
    @Autowired
    private BankDAO bankDAO;

    public OperationResult<BankAccount> addBankAccount() {

        OperationResult<BankAccount> result = new OperationResult();

        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setId(UUID.randomUUID());
        newBankAccount.setOwnerId(UUID.randomUUID());
        newBankAccount.setCreatedAt(DateTime.now());
        newBankAccount.setBalance(BigIntegerFactory.INSTANCE.produceFromInt(0));

        result.setData(newBankAccount);
        result.setSuccess(true);//TODO można wynieść do konstruktora

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
    public OperationResult<Deposit> addBankDeposit() {

        OperationResult<Deposit> result = new OperationResult();

        Deposit deposit = new Deposit();
        deposit.setId(UUID.randomUUID());
        deposit.setBillingPeriod((int)Math.random());
        deposit.setValue(new BigDecimal((int)Math.random()));


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

    public OperationResult<Credit> addBankCredit(){
        OperationResult<Credit> result = new OperationResult();

        Credit credit = new Credit();
        credit.setId(UUID.randomUUID());
        credit.setVaule(new BigDecimal((int)Math.random()));
        credit.setBillingPeriod((int)Math.random());
        credit.setCreditInterestRate(Math.random());


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
}
