package pl.arvanity.currencyconverter.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
public class Calculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double moneyInput;
    private String currencyCodeFrom;
    private String currencyCodeTo;
    private double rate;
    private double moneyOutput;
    private String rateFrom;

    public Calculation(double moneyInput, String currencyCodeFrom, String currencyCodeTo, double rate, double moneyOutput, String rateFrom) {
        this.moneyInput = moneyInput;
        this.currencyCodeFrom = currencyCodeFrom;
        this.currencyCodeTo = currencyCodeTo;
        this.rate = rate;
        this.moneyOutput = moneyOutput;
        this.rateFrom = rateFrom;
    }
}
