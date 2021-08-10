package springbootvuejs.dtos;

public class LoanApplicationDTO {

    private Long id;
    private String name;
    private Double amount;
    private Integer payment;
    private String accountSet;

    public LoanApplicationDTO() {
    }

    public LoanApplicationDTO(Long id, String name, Double amount, Integer payment, String accountSet) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.payment = payment;
        this.accountSet = accountSet;
    }

    public String getAccountSet() {
        return accountSet;
    }

    public void setAccountSet(String accountSet) {
        this.accountSet = accountSet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
