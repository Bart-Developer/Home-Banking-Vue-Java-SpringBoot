package springbootvuejs.controller;

import springbootvuejs.dtos.ClientDTO;
import springbootvuejs.dtos.LoanDTO;
import springbootvuejs.services.ServiceClients;
import springbootvuejs.services.ServiceOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private ServiceClients serviceClients;

    @Autowired
    private ServiceOperations serviceOperations;

    ////////////////////////////////////////////  REQUEST  ///////////////////////////////////////////////////

    @RequestMapping("/clients")
    public List<ClientDTO> getClients() {
        return serviceClients.getAllClients();
    }

    @RequestMapping("/clients/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
    return serviceClients.getClientById(id);
    }

    @RequestMapping("/clients/current")
    public ClientDTO getAll(Authentication authentication) {
        return serviceClients.getClientAuthenticated(authentication);
    }

    @RequestMapping("/loans")
    public List<LoanDTO> getLoans() {
        return serviceOperations.getLoans();
    }
}
