package springbootvuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootvuejs.models.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Account findByNumber(String number);
}
