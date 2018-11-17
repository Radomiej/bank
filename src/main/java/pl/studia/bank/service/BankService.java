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
import pl.studia.bank.model.Kredyt;
import pl.studia.bank.model.Lokata;
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
    public OperationResult<Lokata> addBankDeposit() {

        OperationResult<Lokata> result = new OperationResult();

        Lokata lokata = new Lokata();
        lokata.setId(UUID.randomUUID());
        lokata.setBillingPeriod((int)Math.random());
        lokata.setValue(new BigDecimal((int)Math.random()));


        result.setData(lokata);
        result.setSuccess(true);

        try {
            bankDAO.addBankDeposit(lokata);
        }catch (BankDepositException ex){
            result.setSuccess(false);
            log.error(ex.getMessage());
        }

        return result;
    }

    public OperationResult<Kredyt> addBankCredit(){
        OperationResult<Kredyt> result = new OperationResult();

        Kredyt kredyt = new Kredyt();
        kredyt.setId(UUID.randomUUID());
        kredyt.setVaule(new BigDecimal((int)Math.random()));
        kredyt.setBillingPeriod((int)Math.random());
        kredyt.setCreditInterestRate(Math.random());


        result.setData(kredyt);
        result.setSuccess(true);

        try {
            bankDAO.addBankCredit(kredyt);
        } catch (BankCreditException ex) {
            result.setSuccess(false);
            log.error(ex.getMessage());
        }

        return result;
    }
}
