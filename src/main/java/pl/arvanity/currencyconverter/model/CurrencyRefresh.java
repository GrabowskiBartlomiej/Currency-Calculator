package pl.arvanity.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class CurrencyRefresh {

    private String currency;
    private String code;
    @JsonProperty("mid")
    private double rate;

}
