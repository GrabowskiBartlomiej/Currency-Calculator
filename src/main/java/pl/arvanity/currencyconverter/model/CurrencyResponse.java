package pl.arvanity.currencyconverter.model;

import lombok.Builder;
import lombok.Getter;
import pl.arvanity.currencyconverter.entity.Currency;

@Getter
@Builder
public class CurrencyResponse {

    public static CurrencyResponse of(Currency inputCurrency) {

        return CurrencyResponse.builder()
                .currency(inputCurrency.getCurrency())
                .code(inputCurrency.getCode())
                .rate(inputCurrency.getRate())
                .rateFrom(inputCurrency.getRateFrom())
                .build();
    }

    private final String currency;
    private final String code;
    private final double rate;
    private final String rateFrom;

}
