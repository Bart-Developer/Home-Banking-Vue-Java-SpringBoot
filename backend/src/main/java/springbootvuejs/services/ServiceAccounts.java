package springbootvuejs.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import springbootvuejs.dtos.AccountDTO;
import springbootvuejs.models.Client;
import java.util.Set;

public interface ServiceAccounts {
    Boolean registerNewAccount(Client client);

    ResponseEntity<?> getAllAccountsCurrentClient(Authentication authentication);

    AccountDTO getAccountById(@PathVariable Long id);

    Set<AccountDTO> getAccounts();

    ResponseEntity<?> CreateNewAccount(Authentication authentication, @RequestParam Boolean createNewAccount);
}
