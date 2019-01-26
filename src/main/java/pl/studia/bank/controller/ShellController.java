package pl.studia.bank.controller;

import asg.cliche.Command;
import asg.cliche.ShellFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.studia.bank.helper.BigDecimalFactory;
import pl.studia.bank.helper.BigDecimalOperation;
import pl.studia.bank.model.*;
import pl.studia.bank.service.BankService;
import pl.studia.bank.service.TimeService;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;

@Component
public class ShellController {

    @Autowired
    private BankService bankService;
    @Autowired
    private TimeService timeService;
    @Autowired
    private PaymentController paymentController;
    @Autowired
    private BankController bankController;


    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        Runnable runnable = () -> {
            try {
                ShellFactory.createConsoleShell("bank client", "bank core", this)
                        .commandLoop(); // and three.
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    private BankAccount bankAccount;

    @Command
    public void createBankAccount() throws JsonProcessingException {
        OperationResult<BankAccount> result = bankService.addBankAccount();
        String resultJson = objectMapper.writeValueAsString(result);
        System.out.println(resultJson);

        bankAccount = result.getData();
    }

    @Command
    public void addDeposit(int amount, int duration, int interest) throws JsonProcessingException {
        Deposit deposit = new Deposit();
        fillBasicTimeSaldo(amount, duration, interest, deposit, bankAccount.getId().toString());

        OperationResult<Deposit> result = bankService.addBankDeposit(deposit);
        String resultJson = objectMapper.writeValueAsString(result);
        System.out.println(resultJson);

    }

    @Command
    public void addCredit(int amount, int duration, int interest) throws JsonProcessingException {
        Credit credit = new Credit();
        fillBasicTimeSaldo(amount, duration, interest, credit, bankAccount.getId().toString());

        OperationResult<Credit> result = bankController.addBankCredit(credit).getBody();
        String resultJson = objectMapper.writeValueAsString(result);
        System.out.println(resultJson);

    }

    private void fillBasicTimeSaldo(int amount, int duration, int interest, BasicTimeSaldo basicTimeSaldo, String accountId) {
        BigDecimal real = BigDecimalFactory.INSTANCE.produceFromInt(amount);
        real = BigDecimalOperation.INSTANCE.divide(real, BigDecimalFactory.INSTANCE.hundred);
        basicTimeSaldo.setValue(real);

        Interest interest1 = new Interest();
        interest1.setValue(interest / 100f); //TODO change to BigDecimal
        interest1.setChunk(duration);
        basicTimeSaldo.setInterest(interest1);

        basicTimeSaldo.setCreateTime(timeService.getCurrentTime());
        basicTimeSaldo.setEndTime(timeService.getCurrentTime() + duration);
        basicTimeSaldo.setOwnerBankAccount(accountId);
    }

    @Command
    public void nextTurn(){
        timeService.simulateNextTurn();
    }

    @Command
    public void changeDeposit(int amount){
       if(amount >= 0) paymentController.addBankAccount(bankAccount.getId().toString(), amount);
       else paymentController.subtractBankAccount(bankAccount.getId().toString(), -amount);
    }

    @Command
    public void showSaldo(){
        BankAccount realBankAccount = bankService.getBankAccount(bankAccount.getId().toString()).getData();
        if(realBankAccount == null) {
            System.err.println("Brak konta bankowego o id: " + bankAccount.getId().toString());
            return;
        }
        System.out.println("saldo: " + realBankAccount.getBalance().toString());

    }
}
