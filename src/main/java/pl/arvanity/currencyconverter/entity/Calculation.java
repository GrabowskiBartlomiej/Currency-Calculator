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
    private double inputValue;
    private String currencyCodeFrom;
    private String currencyCodeTo;
    private double rate;
    private double outputValue;
    private String rateFrom;

    public Calculation(double inputValue, String currencyCodeFrom, String currencyCodeTo, double rate, double outputValue, String rateFrom) {
        this.inputValue = inputValue;
        this.currencyCodeFrom = currencyCodeFrom;
        this.currencyCodeTo = currencyCodeTo;
        this.rate = rate;
        this.outputValue = outputValue;
        this.rateFrom = rateFrom;
    }
}
