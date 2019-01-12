package pl.studia.bank.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.studia.bank.model.BankOperation;
import pl.studia.bank.model.OperationResult;
import pl.studia.bank.service.HistoryOperationService;

import java.util.List;

@Controller
@RequestMapping("api/v1/controller")
public class HistoryOperationController {

   @Autowired
    private HistoryOperationService historyOperationService;

    @PostMapping("/findBankOperation")
    public ResponseEntity<BankOperation> findBankOperation(@RequestParam("id") String id){
        BankOperation result = historyOperationService.findBankOperation(id);
        return ResponseEntity.ok(result);
    }


    @PostMapping("/GetAllBankOperation")
    public ResponseEntity<List<BankOperation>> getAllBankOperation(){
        List<BankOperation> result = historyOperationService.getAllBankOperation();
        return ResponseEntity.ok(result);
    }

}
