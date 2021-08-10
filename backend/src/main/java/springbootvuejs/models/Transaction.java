package springbootvuejs.models;

import springbootvuejs.models.Enums.TransactionType;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public long id;

    private TransactionType type;
    private double amount;
    private String description;
    private String date;
    private double balanceFinal;

    //Relations

    //Muchas transacciones para esta cuenta
    @OrderBy
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="accountID")
    public Account account;

    public Transaction() {
    }

    //Constructors

    public Transaction(TransactionType type, double amount, String description, String date, double balanceFinal, Account account) {
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.account = account;
        this.balanceFinal = balanceFinal;
    }

    public Transaction(long id) {
        this.id = id;
    }


    //Get & Set All

    public double getBalanceFinal() {
        return balanceFinal;
    }

    public void setBalanceFinal(double balanceFinal) {
        this.balanceFinal = balanceFinal;
    }

    public long getId() {
        return id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", amount='" + amount + '\'' +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", account=" + account +
                '}';
    }
}

