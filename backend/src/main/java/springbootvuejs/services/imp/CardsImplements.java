package springbootvuejs.services.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import springbootvuejs.dtos.CardDTO;
import springbootvuejs.models.Card;
import springbootvuejs.models.Client;
import springbootvuejs.models.Enums.ColorCard;
import springbootvuejs.models.Enums.TypeCard;
import springbootvuejs.repository.CardRepository;
import springbootvuejs.repository.ClientRepository;
import springbootvuejs.services.ServiceCards;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CardsImplements implements ServiceCards {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private ClientRepository clientRepository;


    @Override
    public List<CardDTO> getAllCards() {
        return this.cardRepository.findAll().stream().map(x -> new CardDTO(x)).collect(Collectors.toList());
    }

    @Override
    public CardDTO getCardById(Long id) {
        return this.cardRepository.findById(id).map(x -> new CardDTO(x)).orElse(null);
    }

    @Override
    public ResponseEntity<?> postNewCard(Authentication authentication, TypeCard typeCard, ColorCard colorCard) {
        Client client = this.clientRepository.findByEmail(authentication.getName()); //OBTENER EL CLIENTE AUTENTIFICADO

        if(client == null){ //COMPARO SI ES NULA LA AUTENTIFICACION
            return new ResponseEntity<>("Authenticated client is not recognized", HttpStatus.FORBIDDEN);
        }

        if(client.getCards().size() >= 6){ //VALIDACION DE CARDS CREADAS POR EL CLIENTE AUTENTIFICADO

            return new ResponseEntity<>("You have a maximum cards permitted: "+ client.getCards().size() , HttpStatus.FORBIDDEN);
        }

        long credit = client.getCards().stream().filter(x-> x.getTypecard().toString().equals("CREDIT")).count();
        long debit = client.getCards().stream().filter(x-> x.getTypecard().toString().equals("DEBIT")).count();

        if(typeCard.toString().equals("CREDIT")&& credit == 3){
            return new ResponseEntity<>("Already have 3 cards credit yet", HttpStatus.FORBIDDEN);
        }

        if(typeCard.toString().equals("DEBIT") && debit == 3 ){
            return new ResponseEntity<>("Already have 3 cards debit yet", HttpStatus.FORBIDDEN);
        }

        String numberRandom = "014/530" + (cardRepository.findAll().size() + 1);

        if (typeCard == null){
            return new ResponseEntity<>("You don't specified the type of card, try again ", HttpStatus.FORBIDDEN);
        }

        if(colorCard == null){
            return new ResponseEntity<>("You don't specified the color of card, try again " , HttpStatus.FORBIDDEN);
        }

        //Genero un numero random de 4 digitos

        int min = 1000;
        int max = 9999;
        int mini= 100;
        int maxi =999;
        int random_card = (int)Math.floor(Math.random()*(max-min+1)+min);
        int random_cvv = (int)Math.floor(Math.random()*(maxi-mini+1)+mini);
        String.valueOf(random_card);
        String.valueOf(random_cvv);

        if(client != null){
            cardRepository.save(new Card(typeCard, colorCard,"2555 2254 4554"+" " + random_card,random_cvv, LocalDateTime.now(),LocalDateTime.now().plusYears(1),client));

            Set<CardDTO> cardDTOS = this.clientRepository.findByEmail(authentication.getName()).getCards().stream().map(CardDTO::new).collect(Collectors.toSet());

            return new ResponseEntity<>(cardDTOS,HttpStatus.OK);
        }

        return new ResponseEntity<>("Param for create is not received ?", HttpStatus.FORBIDDEN);
    }

    @Override
    public ResponseEntity<?> deleteCard(Long id) {
            this.cardRepository.deleteById(id);
            return new ResponseEntity<>("Card Deleted", HttpStatus.OK);
        }
}

