package springbootvuejs.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import springbootvuejs.dtos.ClientDTO;
import springbootvuejs.models.Client;
import springbootvuejs.repository.ClientRepository;
import springbootvuejs.services.ServiceAccounts;
import springbootvuejs.services.ServiceClients;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientsImplements implements ServiceClients {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ServiceAccounts serviceAccounts;

    @Autowired
    private PasswordEncoder passwordEncoder;

    //Register new Client -
    @Override
    public ResponseEntity<Object> registerNewClient(
        @RequestParam String firstName, @RequestParam String lastName,

        @RequestParam String email, @RequestParam String password) {

            if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty()) {

                return new ResponseEntity<>("Missing data", HttpStatus.FORBIDDEN);
            }

            if (clientRepository.findByEmail(email) !=  null) {

                return new ResponseEntity<>("Name already in use", HttpStatus.FORBIDDEN);
            }

            Client client = clientRepository.save(new Client(firstName, lastName, email, passwordEncoder.encode(password)));

            if(client == null){
                return new ResponseEntity<>("This Client is not Autorized", HttpStatus.FORBIDDEN);
            }
            serviceAccounts.registerNewAccount(client);

            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return this.clientRepository.findAll().stream().map(x -> new ClientDTO(x)).collect(Collectors.toList());
    }

    @Override
    public ClientDTO getClientById(Long id) {
        return this.clientRepository.findById(id).map(x -> new ClientDTO(x)).orElse(null);
    }

    @Override
    public ClientDTO getClientAuthenticated(Authentication authentication) {
        Client client = this.clientRepository.findByEmail(authentication.getName());
        return new ClientDTO(client);
    }

}
