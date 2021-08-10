package springbootvuejs.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import springbootvuejs.dtos.CardDTO;
import springbootvuejs.models.Enums.ColorCard;
import springbootvuejs.models.Enums.TypeCard;
import java.util.List;

public interface ServiceCards {
    List<CardDTO> getAllCards();

    CardDTO getCardById(@PathVariable Long id);

    ResponseEntity<?> postNewCard(Authentication authentication, @RequestParam TypeCard typeCard,
                                  @RequestParam ColorCard colorCard);
    ResponseEntity<?> deleteCard(@PathVariable Long id);
}
