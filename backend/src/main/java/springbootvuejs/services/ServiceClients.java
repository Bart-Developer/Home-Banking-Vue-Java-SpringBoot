package springbootvuejs.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import springbootvuejs.dtos.ClientDTO;
import java.util.List;

public interface ServiceClients {

    ResponseEntity<Object> registerNewClient(@RequestParam String firstName, @RequestParam String lastName,
                                             @RequestParam String email, @RequestParam String password);
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(@PathVariable Long id);

    ClientDTO getClientAuthenticated(Authentication authentication);
}
