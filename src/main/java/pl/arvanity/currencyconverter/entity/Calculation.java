package pl.arvanity.currencyconverter.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String currencyCodeFrom;
    private String currencyCodeTo;
    private String rate;
    private String moneyInput;
    private String moneyOutput;
    private String rateFrom;
    @ManyToOne
    private User user;

}
