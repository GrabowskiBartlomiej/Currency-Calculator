package pl.arvanity.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Table {

    private String table;
    private String no;
    private String effectiveDate;
    @JsonProperty("rates")
    private CurrencyRefresh[] currencies;

}
