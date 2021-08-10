package springbootvuejs.controller;

import springbootvuejs.dtos.AccountDTO;
import springbootvuejs.services.ServiceAccounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AccountController {

    @Autowired
    private ServiceAccounts serviceAccounts;

    //Show all repository from account //
    @RequestMapping("/accounts")
    public Set<AccountDTO> getAccounts() {
        return serviceAccounts.getAccounts();
    }

    //Show data from account by ID //
    @RequestMapping("/accounts/{id}")
    public AccountDTO getAccount(@PathVariable Long id){
        return serviceAccounts.getAccountById(id);
    }

    //Show data from accounts current client //
    @RequestMapping("/clients/current/accounts")
    public ResponseEntity<?> getAllAccountsCurrentClient(Authentication authentication) {
        return serviceAccounts.getAllAccountsCurrentClient(authentication);
    }

    //Permissions to create new Accounts//
    @PostMapping("/clients/current/accounts")
    public ResponseEntity<?> postAccount(Authentication authentication, @RequestParam Boolean createNewAccount){
        return serviceAccounts.CreateNewAccount(authentication, createNewAccount);
    }

}
