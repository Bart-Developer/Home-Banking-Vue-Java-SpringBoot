package springbootvuejs.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import springbootvuejs.dtos.AccountDTO;
import springbootvuejs.models.Account;
import springbootvuejs.models.Client;
import springbootvuejs.repository.AccountRepository;
import springbootvuejs.repository.ClientRepository;
import springbootvuejs.services.ServiceAccounts;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccountServicesImplements implements ServiceAccounts {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Boolean registerNewAccount(Client client){

        String numberRandom = "014/530" + (accountRepository.findAll().size() + 1);

        if(accountRepository.findAll().size() <= 9999){
            accountRepository.save(new Account(numberRandom,00.000,client, LocalDateTime.now()));
            return true;
        }

        return false;
    }

    //Show data from accounts current client //
    @Override
    public ResponseEntity<?> getAllAccountsCurrentClient(Authentication authentication) {

        Client client = this.clientRepository.findByEmail(authentication.getName());

        if(client == null){
            return new ResponseEntity<>("Not recognized as a valid customer", HttpStatus.FORBIDDEN);
        }

        Set<AccountDTO> accounts = client.getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());

        return new ResponseEntity<>( accounts, HttpStatus.OK);

    }

    @Override
    public AccountDTO getAccountById(Long id) {
        return  this.accountRepository.findById(id).map(x -> new AccountDTO(x)).orElse(null);
    }

    @Override
    public Set<AccountDTO> getAccounts() {
        return this.accountRepository.findAll().stream().map(x -> new AccountDTO(x)).collect(Collectors.toSet());
    }

    //Permissions to create new Accounts//
    @Override
    public ResponseEntity<?> CreateNewAccount(Authentication authentication, Boolean createNewAccount) {

        Client client = this.clientRepository.findByEmail(authentication.getName()); //OBTENER EL CLIENTE AUTENTIFICADO

        if(client == null){ //COMPARO SI ES NULA LA AUTENTIFICACION

            return new ResponseEntity<>("Authenticated client is not recognized", HttpStatus.FORBIDDEN);
        }

        if(client.getAccounts().size() >= 3){ //VALIDACION DE CUENTAS CREADAS POR EL CLIENTE AUTENTIFICADO

            return new ResponseEntity<>("You have a maximum accounts permitted", HttpStatus.FORBIDDEN);
        }

        String numberRandom = "014/530" + (accountRepository.findAll().size() + 1);

        if(accountRepository.findAll().size() > 9999){
            return new ResponseEntity<>("maximum of all accounts published ", HttpStatus.CONFLICT); //VALIDACION DE CANTIDAD DE CUENTAS CREADAS EN TOTAL
        }

        if (createNewAccount){
            accountRepository.save(new Account(numberRandom,00.00,client, LocalDateTime.now()));
            Set<AccountDTO> accountDTO = this.clientRepository.findByEmail(authentication.getName()).getAccounts().stream().map(AccountDTO::new).collect(Collectors.toSet());
            return new ResponseEntity<>(accountDTO,HttpStatus.OK);
        }

        return new ResponseEntity<>("Param for create is not received ?", HttpStatus.FORBIDDEN);
    };





}

