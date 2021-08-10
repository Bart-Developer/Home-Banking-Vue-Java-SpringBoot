package springbootvuejs.dtos;

import springbootvuejs.models.Loan;
import java.util.List;

public class LoanDTO {

    private Long id;
    private String name;
    private Double maxamount;
    private List<Integer> payments;

    public LoanDTO() {
    }

    public LoanDTO(Loan loan) {
        this.id = loan.getId();
        this.name = loan.getName();
        this.maxamount = loan.getMaxamount();
        this.payments = loan.getPayments();
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

    public Double getMaxamount() {
        return maxamount;
    }

    public void setMaxamount(Double maxamount) {
        this.maxamount = maxamount;
    }

    public List<Integer> getPayments() {
        return payments;
    }

    public void setPayments(List<Integer> payments) {
        this.payments = payments;
    }
}