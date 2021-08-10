package springbootvuejs.dtos;

import springbootvuejs.models.ClientLoan;
import java.time.LocalDate;

public class ClientLoanDTO {

    private String name;
    private Long loanId;
    private Long clientLoanId;
    private Double amount;
    private Integer payment;
    private LocalDate dateRequest;


    public ClientLoanDTO() {
    }

    public ClientLoanDTO(ClientLoan clientLoan) {
        this.loanId = clientLoan.getLoan().getId();
        this.clientLoanId = clientLoan.getId();
        this.amount = clientLoan.getAmount();
        this.payment = clientLoan.getPayment();
        this.name = clientLoan.getName();
        this.dateRequest= clientLoan.getDateRequest();
    }

    //GET and Setters

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

    public Long getLoanId() {
        return loanId;
    }

    public void setLoanId(Long loanId) {
        this.loanId = loanId;
    }

    public Long getClientLoanId() {
        return clientLoanId;
    }

    public void setClientLoanId(Long clientLoanId) {
        this.clientLoanId = clientLoanId;
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


}
