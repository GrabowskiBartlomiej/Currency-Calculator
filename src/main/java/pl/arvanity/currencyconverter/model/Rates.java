package pl.arvanity.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {

    private String currency;
    private String code;
    @JsonProperty("mid")
    private double rate;

}
