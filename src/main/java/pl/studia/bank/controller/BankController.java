package pl.studia.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.studia.bank.model.BankAccount;
import pl.studia.bank.model.Credit;
import pl.studia.bank.model.Deposit;
import pl.studia.bank.model.OperationResult;
import pl.studia.bank.service.BankService;


@Controller
@RequestMapping("api/v1/controller")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/addBankAccount")
    public ResponseEntity<OperationResult<BankAccount>> addBankAccount(){
        OperationResult<BankAccount> result = bankService.addBankAccount();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/deleteBankAccount")
    public ResponseEntity<OperationResult<BankAccount>> deleteBankAccount(@RequestParam("id") String id){
        OperationResult<BankAccount> deletedBankAccount = bankService.deleteBankAccount(id);
        return ResponseEntity.ok(deletedBankAccount);
    }

    @PostMapping("/addBankDeposit")
    public ResponseEntity<OperationResult<Deposit>> addBankDeposit(@RequestParam("depositValue")int depositValue){
        OperationResult<Deposit> result = bankService.addBankDeposit(depositValue);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addBankCredit")
    public ResponseEntity<OperationResult<Credit>> addBankCredit(@RequestBody Credit credit){
        OperationResult<Credit> result = bankService.addBankCredit(credit);
        return ResponseEntity.ok(result);
    }

}
