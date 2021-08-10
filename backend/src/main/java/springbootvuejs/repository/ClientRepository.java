package springbootvuejs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootvuejs.models.Client;

public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findByEmail(String email);
}
