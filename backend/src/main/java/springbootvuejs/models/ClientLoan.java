package springbootvuejs.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class ClientLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;

    private String name;
    private Double amount;
    private Integer payment;
    private LocalDate dateRequest;

    //relations

    //Muchos clientes pueden solicitar un prestamo

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="ClientId")
    private Client client;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="LoanId")
    private Loan loan;

    public ClientLoan() {
    }

    //Constructor General

    public ClientLoan(Double amount, Integer payment, LocalDate dateRequest , Client client, Loan loan) {
        this.name = loan.getName();
        this.amount = amount;
        this.payment = payment;
        this.client = client;
        this.loan = loan;
        this.dateRequest= dateRequest;
    }

    //Get and Setters

    public LocalDate getDateRequest() {
        return dateRequest;
    }

    public void setDateRequest(LocalDate dateRequest) {
        this.dateRequest = dateRequest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }
}

