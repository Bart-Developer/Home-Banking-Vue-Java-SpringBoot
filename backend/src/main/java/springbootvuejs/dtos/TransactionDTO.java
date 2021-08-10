package springbootvuejs.dtos;

import springbootvuejs.models.Enums.TransactionType;
import springbootvuejs.models.Transaction;

public class TransactionDTO{

    public long id;
    private TransactionType type;
    private double amount;
    private String description;
    private String date;
    private double balanceFinal;

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.description = transaction.getDescription();
        this.date = transaction.getDate();
        this.balanceFinal = transaction.getBalanceFinal();
    }

    public double getBalanceFinal() {
        return balanceFinal;
    }

    public void setBalanceFinal(double balanceFinal) {
        this.balanceFinal = balanceFinal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
