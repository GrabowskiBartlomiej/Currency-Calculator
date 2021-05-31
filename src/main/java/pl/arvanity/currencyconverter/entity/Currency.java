package pl.arvanity.currencyconverter.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "currencies")
public class Currency {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String currency;
    private String code;
    private double rate;
    private String rateFrom;

    public Currency(String currency, String code, double rate, String rateFrom) {
        this.currency = currency;
        this.code = code;
        this.rate = rate;
        this.rateFrom = rateFrom;
    }

}
