package pl.studia.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.studia.bank.model.OperationResult;

@Controller
@RequestMapping("api/v1/controller")
public class BankController {

    @PostMapping("/addBankAccount")
    public ResponseEntity<OperationResult> AddBankAccount(){

        return ResponseEntity.notFound().build();
    }
}
