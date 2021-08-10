package springbootvuejs.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import springbootvuejs.dtos.LoanApplicationDTO;
import springbootvuejs.dtos.LoanDTO;
import springbootvuejs.models.*;
import springbootvuejs.models.Enums.TransactionType;
import springbootvuejs.repository.*;
import springbootvuejs.services.ServiceOperations;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OperationsServiceImplements implements ServiceOperations {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ClientLoanRepository clientLoanRepository;

    @Autowired
    private LoanRepository loanRepository;


    @Override
    public List<LoanDTO> getLoans() {
        return this.loanRepository.findAll().stream().map(x -> new LoanDTO(x)).collect(Collectors.toList());
    }

    //Make a transaction from an account to other account
    @Override
    public ResponseEntity<?> postTransactions(Authentication authentication, Double amount, String description, String originAccount, String destinationAccount) {
        Client client = this.clientRepository.findByEmail(authentication.getName());

        if(client == null){

            return new ResponseEntity<>("Authenticated client is not recognized", HttpStatus.FORBIDDEN);
        }

        if(amount <= 0){
            return new ResponseEntity<>("Invalid amount, please try again", HttpStatus.FORBIDDEN);
        }

        if (originAccount.isEmpty() || destinationAccount.isEmpty()) {
            return new ResponseEntity<>("Please fill in all the fields of the form", HttpStatus.FORBIDDEN);
        }

        if(originAccount.equalsIgnoreCase(destinationAccount)){
            return new ResponseEntity<>("Operation invalid between account origin and account destiny - Validations 1", HttpStatus.FORBIDDEN);
        }

        Account origAccount = this.accountRepository.findByNumber(originAccount);
        Account destAccount = this.accountRepository.findByNumber(destinationAccount);

        if(destAccount == null){
            return new ResponseEntity<>("the destination account cannot be found, please try again", HttpStatus.FORBIDDEN);
        }

        if(!client.getAccounts().contains(origAccount)) {   // va a comparar que ambas cuentas (origen (client autentificado) y destino) existan
            return new ResponseEntity<>("Operation invalid between account origin and account destiny - the accounts are equals", HttpStatus.FORBIDDEN);
        }

        if(origAccount.getBalance()< amount){ // compara el monto ingresado con el saldo de la cuenta
            return new ResponseEntity<>("Invalid amount -> [ $"+ amount + " ] When your balance is: $" + origAccount.getBalance()  , HttpStatus.FORBIDDEN);
        }

        // ALGORITHM CALCULATORS //

        double origCurrentBalance = origAccount.getBalance() - amount;
        origAccount.setBalance(origCurrentBalance);

        double destCurrentBalance = destAccount.getBalance()+amount;
        destAccount.setBalance(destCurrentBalance);

        transactionRepository.save(new Transaction(TransactionType.CREDITO, amount, "Transfer by Acc/N°: " + origAccount.getNumber() +" "+ description, LocalDateTime.now().toString(), destCurrentBalance, destAccount));
        transactionRepository.save(new Transaction(TransactionType.DEBITO, -amount, "Transfer to Acc/N°: " + destAccount.getNumber() +" "+ description, LocalDateTime.now().toString(), origCurrentBalance, origAccount ));

        return new ResponseEntity<>("Successful transfer ",HttpStatus.OK);
    }

    //Make a Loan to some account logged.
    @Override
    public ResponseEntity<?> postLoans(Authentication authentication, LoanApplicationDTO loanApplicationDTO) {
        Client client = this.clientRepository.findByEmail(authentication.getName());
        Account destinyAccount = this.accountRepository.findByNumber(loanApplicationDTO.getAccountSet());

        if(loanApplicationDTO.getAmount() < 5000){
            return new ResponseEntity<>("the minimum amount is $5.000", HttpStatus.FORBIDDEN);
        }

        if (client == null) {
            return new ResponseEntity<>("Authenticated client is not recognized", HttpStatus.FORBIDDEN);
        }

        if (destinyAccount == null) {
            return new ResponseEntity<>("The account entered is incorrect", HttpStatus.FORBIDDEN);
        }

        if(!client.getAccounts().contains(destinyAccount)){
            return new ResponseEntity<>("The account entered is not valid to the client authentication", HttpStatus.FORBIDDEN);
        }

        if (loanApplicationDTO.getId() == null || loanApplicationDTO.getName().isEmpty() || loanApplicationDTO.getAmount() == null || loanApplicationDTO.getPayment() <= 0 ) {
            return new ResponseEntity<>("Please fill in all the fields of the form", HttpStatus.FORBIDDEN);
        }

        Optional<Loan> loanRequest = this.loanRepository.findById(loanApplicationDTO.getId());

        if(loanRequest.isEmpty()){
            return new ResponseEntity<>("esta vacio el optional", HttpStatus.FORBIDDEN);
        }

        if (!loanRequest.get().getName().contains(loanApplicationDTO.getName())|| !loanRequest.get().getPayments().contains(loanApplicationDTO.getPayment())) {

            return new ResponseEntity<>("The values entered do not match the type of loan requested", HttpStatus.FORBIDDEN);
        }

        if(loanApplicationDTO.getAmount() > loanRequest.get().getMaxamount()){

            return new ResponseEntity<>("The value amount entered do not match the type of loan requested", HttpStatus.FORBIDDEN);
        }

        clientLoanRepository.save(new ClientLoan(loanApplicationDTO.getAmount() + loanApplicationDTO.getAmount()*20/100,loanApplicationDTO.getPayment(), LocalDate.now(), client, loanRequest.get()));

        double destinyCurrentBalance = destinyAccount.getBalance() + loanApplicationDTO.getAmount();
        destinyAccount.setBalance(destinyCurrentBalance);

        transactionRepository.save(new Transaction(TransactionType.CREDITO, loanApplicationDTO.getAmount(), "Loan Approved of type: " + loanApplicationDTO.getName() , LocalDateTime.now().toString(),destinyCurrentBalance, destinyAccount));

        return new ResponseEntity<>("Loan Completed", HttpStatus.CREATED);
    }
}
