package springbootvuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootvuejs.models.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long> {
}
