package springbootvuejs.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import springbootvuejs.dtos.LoanApplicationDTO;
import springbootvuejs.dtos.LoanDTO;
import java.util.List;

public interface ServiceOperations {

    List<LoanDTO> getLoans();

    ResponseEntity<?> postTransactions(Authentication authentication,
                                       @RequestParam Double amount,
                                       @RequestParam String description,
                                       @RequestParam String originAccount,
                                       @RequestParam String destinationAccount );

    ResponseEntity<?> postLoans(Authentication authentication, @RequestBody LoanApplicationDTO loanApplicationDTO);

}
