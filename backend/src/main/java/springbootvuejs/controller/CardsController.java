package springbootvuejs.controller;

import springbootvuejs.dtos.CardDTO;
import springbootvuejs.models.Enums.ColorCard;
import springbootvuejs.models.Enums.TypeCard;
import springbootvuejs.services.ServiceCards;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api")
public class CardsController {

    @Autowired
    private ServiceCards serviceCards;

    @RequestMapping("/cards")
    public List<CardDTO> getCards() {
        return serviceCards.getAllCards();
    }

    @RequestMapping("/cards/{id}")
    public CardDTO getCard(@PathVariable Long id){
       return serviceCards.getCardById(id);
    }


    @PostMapping("/clients/current/cards")
    public ResponseEntity<?> postAccount(Authentication authentication, @RequestParam TypeCard typeCard, @RequestParam ColorCard colorCard) {
    return serviceCards.postNewCard(authentication, typeCard, colorCard);
    }

    @DeleteMapping("/cards/{id}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id){
        return serviceCards.deleteCard(id);
    }
}
