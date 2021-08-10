package springbootvuejs.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private long id;

    private String number;
    public LocalDateTime creationDate;
    private double balance;

    //Relations

    //Muchas cuentas para un cliente

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "clientID")
    public Client client;


    //Esta cuenta puede tener muchas transacciones.
    @OrderBy
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    public Set<Transaction> transactions = new HashSet<>();


    public Account() {
    }

    //Constructors

    public Account(String number, double balance, Client client, LocalDateTime creationDate) {
        this.number = number;
        this.balance = balance;
        this.client = client;
        this.creationDate = creationDate;
    }

    //Get & Set All

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(Set<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", creationDate=" + creationDate +
                ", balance='" + balance + '\'' +
                ", client=" + client +
                ", transactions=" + transactions +
                '}';
    }

}