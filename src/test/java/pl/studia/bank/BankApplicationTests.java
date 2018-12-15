package pl.studia.bank;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.studia.bank.service.BankService;
import pl.studia.bank.service.TimeService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BankApplicationTests {

    @Autowired
    private BankService bankService;



	@Autowired
	private TimeService timeService;

	@Test
	public void contextLoads() {
        bankService.addBankAccount();

	    for(int i = 0; i < 100; i++){
            timeService.symulateNextTurn();
        }
	}

}
