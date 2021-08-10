package springbootvuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootvuejs.models.Card;

public interface CardRepository extends JpaRepository<Card,Long> {
}
