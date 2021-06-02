package pl.arvanity.currencyconverter.controller.currency;

import lombok.Builder;
import lombok.Getter;
import pl.arvanity.currencyconverter.entity.Currency;

@Getter
@Builder
public class CurrencyResponse {

    public static CurrencyResponse of(Currency inputCurrency){

        return CurrencyResponse.builder()
                .currency(inputCurrency.getCurrency())
                .code(inputCurrency.getCode())
                .rate(inputCurrency.getRate())
                .rateFrom(inputCurrency.getRateFrom())
                .build();
    }

    private String currency;
    private String code;
    private double rate;
    private String rateFrom;

}
