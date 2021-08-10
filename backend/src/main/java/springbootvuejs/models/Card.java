package springbootvuejs.models;

import springbootvuejs.models.Enums.ColorCard;
import springbootvuejs.models.Enums.TypeCard;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    public Long id;

    private String cardholder; //get name & last name from client
    private TypeCard typecard;
    private ColorCard colorCard;
    private String number;
    private Integer cvv;
    private LocalDateTime fromDate;
    private LocalDateTime thruDate;

    //Relations
    //Muchos clientes pueden acceder/obtener esta tarjeta

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="clientId")
    private Client client;

    public Card() {
    }

    //Constructor

    public Card(TypeCard typecard, ColorCard colorCard, String number, Integer cvv,  LocalDateTime fromDate, LocalDateTime thruDate, Client client) {
        this.cardholder = client.getFirstName() + " " + client.getLastName();
        this.typecard = typecard;
        this.colorCard = colorCard;
        this.number = number;
        this.cvv = cvv;
        this.thruDate = LocalDateTime.parse(String.format(thruDate.toString()));
        this.fromDate = fromDate;
        this.client = client;
    }

    //GET && SETTERS

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getThruDate() {
        return thruDate;
    }

    public void setThruDate(LocalDateTime thruDate) {
        this.thruDate = thruDate;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDateTime fromDate) {
        this.fromDate = fromDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }


}

