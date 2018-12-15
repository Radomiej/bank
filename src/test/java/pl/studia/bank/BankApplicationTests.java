package pl.studia.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.studia.bank.helper.BigDecimalFactory;
import pl.studia.bank.model.*;
import pl.studia.bank.service.BankService;
import pl.studia.bank.service.TimeService;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {

    @Autowired
    private BankService bankService;



	@Autowired
	private TimeService timeService;

	@Test
	public void contextLoads() {
        OperationResult<BankAccount> addBankResult =  bankService.addBankAccount();
        assertNotNull(addBankResult);
        BankAccount bankAccount = addBankResult.getData();
        assertNotNull(bankAccount);

        Deposit deposit = createTestDeposit(bankAccount.getId().toString(),3, 1000, 0.10d);

        OperationResult<Deposit> addDepositResult = bankService.addBankDeposit(deposit);
        assertNotNull(addDepositResult);

	    for(int i = 0; i < 3; i++){
            timeService.simulateNextTurn();
        }

        System.out.println("End Balance: " + bankAccount.getBalance());

	    Credit credit = createTestCredit(bankAccount.getId().toString(), 5, 1000, 0.20d);
        bankService.addBankCredit(credit);

        for(int i = 0; i < 5; i++){
            timeService.simulateNextTurn();
        }

        System.out.println("End Balance: " + bankAccount.getBalance());
	}

    private Credit createTestCredit(String accountId, int duration, double depositValue, double interestValue) {
        Interest interest = new Interest();
        interest.setChunk(1);
        interest.setValue(interestValue);

        Credit credit = new Credit();
        credit.setId(UUID.randomUUID());
        int currentTime = timeService.getCurrentTime();
        credit.setCreateTime(currentTime);
        credit.setEndTime(currentTime + duration);
        credit.setInterest(interest);
        credit.setVaule(BigDecimalFactory.INSTANCE.produceFromDouble(depositValue));
        credit.setOwnerBankAccount(accountId);
        return credit;
    }

    private Deposit createTestDeposit(String accountId, int duration, double depositValue, double interestValue) {
        Interest interest = new Interest();
        interest.setChunk(1);
        interest.setValue(interestValue);

        Deposit deposit = new Deposit();
        deposit.setId(UUID.randomUUID());
        int currentTime = timeService.getCurrentTime();
        deposit.setCreateTime(currentTime);
        deposit.setEndTime(currentTime + duration);
        deposit.setInterest(interest);
        deposit.setVaule(BigDecimalFactory.INSTANCE.produceFromDouble(depositValue));
        deposit.setOwnerBankAccount(accountId);
        return deposit;
    }

}
