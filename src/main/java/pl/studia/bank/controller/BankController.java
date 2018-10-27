package pl.studia.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.studia.bank.model.OperationResult;
import pl.studia.bank.service.BankService;

@Controller
@RequestMapping("api/v1/controller")
public class BankController {

    @Autowired
    private BankService bankService;

    @PostMapping("/addBankAccount")
    public ResponseEntity<OperationResult> addBankAccount(){
        bankService.addBankAccount();
        return ResponseEntity.notFound().build();
    }
}
