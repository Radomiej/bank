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
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.Deposit;
import pl.studia.bank.model.Interest;
import pl.studia.bank.model.OperationResult;
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

    @Command
    public void createBankAccount() throws JsonProcessingException {
        OperationResult<BankAccount> result = bankService.addBankAccount();
        String resultJson = objectMapper.writeValueAsString(result);
        System.out.println(resultJson);

    }

    @Command
    public void createDeposit(String owner, int amount, int duration, int interest) throws JsonProcessingException {
        Deposit deposit = new Deposit();
        BigDecimal real = BigDecimalFactory.INSTANCE.produceFromInt(amount);
        real = BigDecimalOperation.INSTANCE.divide(real, BigDecimalFactory.INSTANCE.hundred);
        deposit.setVaule(real);

        Interest interest1 = new Interest();
        interest1.setValue(interest / 100); //TODO change to BigDecimal
        deposit.setInterest(interest1);

        deposit.setCreateTime(timeService.getCurrentTime());
        deposit.setEndTime(timeService.getCurrentTime() + duration);
        deposit.setOwnerBankAccount(owner);

        OperationResult<Deposit> result = bankService.addBankDeposit(deposit);
        String resultJson = objectMapper.writeValueAsString(result);
        System.out.println(resultJson);

    }

    @Command
    public void nextTurn(){
        timeService.simulateNextTurn();
    }
}
