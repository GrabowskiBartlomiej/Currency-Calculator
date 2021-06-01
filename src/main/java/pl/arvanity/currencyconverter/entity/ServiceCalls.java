package pl.arvanity.currencyconverter.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Data
@Entity
public class ServiceCalls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String method;
    private String action;
    private double moneyInput;
    private String currencyCodeFrom;
    private String currency;
    private String currencyCodeTo;
    private double moneyOutput;
    private double rate;
    private String rateFrom;
    private Date operationDate = new Date();
}
