package pl.studia.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.studia.bank.dao.BankDAO;
import pl.studia.bank.helper.BigDecimalFactory;
import pl.studia.bank.service.BankOperationService;

import java.math.BigDecimal;

@Controller
@RequestMapping("api/v1/payment")
public class PaymentController {
    @Autowired
    private BankDAO bankDAO;

    @Autowired
    private BankOperationService bankOperationService;

    @PostMapping("/deposit")
    public ResponseEntity<Boolean> addBankAccount(@RequestParam("accountId") String accountId, @RequestParam("value") long rawValue){
        BigDecimal value = BigDecimalFactory.INSTANCE.produceFromLong(rawValue);
        value = value.divide(BigDecimalFactory.INSTANCE.produceFromInt(100));
        bankOperationService.addToAccount(accountId, value);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/deposit")
    public ResponseEntity<Boolean> substractBankAccount(@RequestParam("accountId") String accountId, @RequestParam("value") long rawValue){
        BigDecimal value = BigDecimalFactory.INSTANCE.produceFromLong(rawValue);
        value = value.divide(BigDecimalFactory.INSTANCE.produceFromInt(100));
        bankOperationService.substractFromAccount(accountId, value);
        return ResponseEntity.ok(true);
    }
}
