package springbootvuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootvuejs.models.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
