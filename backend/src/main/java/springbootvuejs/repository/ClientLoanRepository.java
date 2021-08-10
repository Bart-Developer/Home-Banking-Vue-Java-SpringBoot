package springbootvuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootvuejs.models.ClientLoan;

public interface ClientLoanRepository extends JpaRepository<ClientLoan,Long> {
}
