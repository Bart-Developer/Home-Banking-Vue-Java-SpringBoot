package springbootvuejs.dtos;

import springbootvuejs.models.Card;
import springbootvuejs.models.Enums.ColorCard;
import springbootvuejs.models.Enums.TypeCard;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class CardDTO{

    private Long cardId;
    private String cardholder; //get name & last name from client
    private TypeCard typecard;
    private ColorCard colorCard;
    private String number;
    private Integer cvv;
    private String fromDate;
    private String thruDate;
    private Set<String> client = new HashSet<>();

    public CardDTO() {
    }

    public CardDTO(Card card) {
        this.cardId = card.getId();
        this.cardholder = card.getCardholder();
        this.typecard = card.getTypecard();
        this.colorCard = card.getColorCard();
        this.number = card.getNumber();
        this.cvv = card.getCvv();
        this.fromDate = formatterDate(card.getFromDate());
        this.thruDate = formatterDate(card.getThruDate());
        this.client.add(card.getClient().firstName);
        this.client.add(card.getClient().lastName);
        this.client.add(card.getClient().email);
    }
    public  String formatterDate(LocalDateTime dateTime){
        String[] valueOne;
        String date;
        valueOne =  dateTime.truncatedTo(ChronoUnit.DAYS).toString().split("T" + "00:00",+ -3);
        date = valueOne[0];
        date = date.substring(2,7);
        return date;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public TypeCard getTypecard() {
        return typecard;
    }

    public void setTypecard(TypeCard typecard) {
        this.typecard = typecard;
    }

    public ColorCard getColorCard() {
        return colorCard;
    }

    public void setColorCard(ColorCard colorCard) {
        this.colorCard = colorCard;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getCvv() {
        return cvv;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getThruDate() {
        return thruDate;
    }

    public void setThruDate(String thruDate) {
        this.thruDate = thruDate;
    }

    public Set<String> getClient() {
        return client;
    }

    public void setClient(Set<String> client) {
        this.client = client;
    }
}
