package springbootvuejs.controller;

import springbootvuejs.dtos.LoanApplicationDTO;
import springbootvuejs.services.ServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class OperationsController {

    @Autowired
    private ServiceOperations serviceOperations;

    @Transactional
    @PostMapping("/clients/current/transfer")
    public ResponseEntity<?> postTransactions(Authentication authentication,
                                              @RequestParam Double amount,
                                              @RequestParam String description,
                                              @RequestParam String originAccount,
                                              @RequestParam String destinationAccount ){
       return serviceOperations.postTransactions(authentication, amount, description, originAccount, destinationAccount );
    }

    @Transactional
    @PostMapping("/clients/current/loan")
    public ResponseEntity<?> postLoans(Authentication authentication, @RequestBody LoanApplicationDTO loanApplicationDTO) {

       return serviceOperations.postLoans(authentication, loanApplicationDTO);
    }
}
