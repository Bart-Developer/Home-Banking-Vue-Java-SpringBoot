package springbootvuejs.repository;

import org.springframework.data.repository.CrudRepository;
import springbootvuejs.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
