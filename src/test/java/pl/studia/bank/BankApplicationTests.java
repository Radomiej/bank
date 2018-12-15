package pl.studia.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.studia.bank.helper.BigIntegerFactory;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.Deposit;
import pl.studia.bank.model.Interest;
import pl.studia.bank.model.OperationResult;
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

        Deposit deposit = createTestDeposit(bankAccount.getId().toString(),3, 1000, 10d);

        OperationResult<Deposit> addDepositResult = bankService.addBankDeposit(deposit);
        assertNotNull(addDepositResult);

	    for(int i = 0; i < 100; i++){
            timeService.symulateNextTurn();
        }

        System.out.println("End Balance: " + bankAccount.getBalance());
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
        deposit.setVaule(BigIntegerFactory.INSTANCE.produceFromDouble(depositValue));
        deposit.setOwnerBankAccount(accountId);
        return deposit;
    }

}
