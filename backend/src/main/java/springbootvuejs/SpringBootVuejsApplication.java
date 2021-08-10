package springbootvuejs;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import springbootvuejs.models.*;
import springbootvuejs.models.Enums.ColorCard;
import springbootvuejs.models.Enums.TransactionType;
import springbootvuejs.models.Enums.TypeCard;
import springbootvuejs.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootVuejsApplication {

	@Autowired
	PasswordEncoder passwordEnconder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootVuejsApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(AccountRepository accountsRepository,
									  ClientRepository clientRepository,
									  TransactionRepository transactionRepository,
									  LoanRepository loanRepository,
									  ClientLoanRepository clientLoanRepository,
									  CardRepository cardRepository) {
		return (args) -> {

//CLIENTES:
			Client client1 = clientRepository.save(new Client("Bartolome","Albarracin","bartolome.albarracin@hotmail.com",passwordEnconder.encode("2859")));
			Client client2 = clientRepository.save(new Client("Uko","Benelli", "nicouko.benelli@gmail.com",passwordEnconder.encode("5889")));
			Client client3 = clientRepository.save(new Client("Federico", "Battcock","fede_mataperros@hotmail.com",passwordEnconder.encode("123456")));
			Client client4 = clientRepository.save(new Client("Claudia", "Piña","clau_pillinbqca@hotmail.com", passwordEnconder.encode("89885")));
			Client admin = clientRepository.save(new Client("admin", "admin","admin", passwordEnconder.encode("admin")));

//CUENTAS:
			Account account1 = accountsRepository.save(new Account("014/5300", 15000, client1, LocalDateTime.now()));
			Account account2 = accountsRepository.save(new Account ("014/5331", 16000, client1, LocalDateTime.now().plusDays(1)));
			Account account3 = accountsRepository.save(new Account("014/5320", 11030, client2, LocalDateTime.now()));
			Account account4 = accountsRepository.save(new Account("014/5885",27000,client3, LocalDateTime.now()));
			Account account5 = accountsRepository.save(new Account("014/5886",7000,client3, LocalDateTime.now()));
			Account account6 = accountsRepository.save(new Account("014/5887",3000,client3, LocalDateTime.now()));
			Account account7 = accountsRepository.save(new Account("014/5847",20000.00,client4, LocalDateTime.now()));

//TRANSACCIONES
			Transaction transaction1 = transactionRepository.save(new Transaction(TransactionType.CREDITO,15000,"salary payment","2021-05-23",15000,account1));
			Transaction transaction4 = transactionRepository.save(new Transaction(TransactionType.CREDITO,16000,"salary payment","2021-05-23",16000,account2));
			Transaction transaction7 = transactionRepository.save(new Transaction(TransactionType.CREDITO,16000,"salary payment","23-05-21",1,account7));
			Transaction transaction8 = transactionRepository.save(new Transaction(TransactionType.DEBITO,-9000,"Withdrawal of money","24-05-21",1,account7));
			Transaction transaction9 = transactionRepository.save(new Transaction(TransactionType.DEBITO,-2000,"Transfer Money to account","24-05-21",1,account7));

//PRESTAMOS
			Loan hipotecario = loanRepository.save( new Loan("Mortgage",500000.00, Arrays.asList(6,12,24,48,60))); //id 1
			Loan personal = loanRepository.save( new Loan("Personal",100000.00, Arrays.asList(6,12,24)));  //id 2
			Loan automotriz = loanRepository.save( new Loan("Motor-Vehicle",300.000, Arrays.asList(6,12,24,36))); // id 3

//PRESTAMOS A CLIENTES
			ClientLoan clientLoan1 = clientLoanRepository.save(new ClientLoan(400000.00,60, LocalDate.of(2021,4,12) ,client1,hipotecario));
			ClientLoan clientLoan2 = clientLoanRepository.save(new ClientLoan(100000.00,24,LocalDate.of(2021,5,18),client1,personal));
			ClientLoan clientLoan3 = clientLoanRepository.save(new ClientLoan(200000.00,36,LocalDate.of(2021,6,2),client1,automotriz));
			ClientLoan clientLoan4= clientLoanRepository.save(new ClientLoan(300000.00,24,LocalDate.of(2021,4,2),client2,automotriz));
// TARJETAS
			Card card1 = cardRepository.save(new Card(TypeCard.DEBIT, ColorCard.GOLD,"2588 5455 7786 2255",322,LocalDateTime.now(),LocalDateTime.now().plusYears(1),client1));
			Card card2 = cardRepository.save(new Card(TypeCard.CREDIT,ColorCard.TITANIUM,"2588 5355 7768 4458",842,LocalDateTime.now(),LocalDateTime.now().plusYears(1),client1));
			Card card3 = cardRepository.save(new Card(TypeCard.CREDIT, ColorCard.GOLD,"2588 5355 7768 5458",895,LocalDateTime.now(),LocalDateTime.now().plusYears(1),client4));

		};
	}
}