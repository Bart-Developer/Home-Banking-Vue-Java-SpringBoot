package springbootvuejs.controller;

import springbootvuejs.services.ServiceClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class NewClientController {

    @Autowired
    private ServiceClients serviceClients;

    @RequestMapping(path = "/clients", method = RequestMethod.POST)
    public ResponseEntity<Object> register(
            @RequestParam String firstName, @RequestParam String lastName,
            @RequestParam String email, @RequestParam String password){

        return serviceClients.registerNewClient(firstName, lastName, email, password);
    }
}
