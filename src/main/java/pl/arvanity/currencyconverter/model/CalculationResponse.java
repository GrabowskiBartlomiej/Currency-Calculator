package pl.arvanity.currencyconverter.model;

import lombok.Builder;
import lombok.Getter;
import pl.arvanity.currencyconverter.entity.Calculation;

@Getter
@Builder
public class CalculationResponse {

    public static CalculationResponse of(Calculation inputCalculation) {
        return CalculationResponse.builder()
                .inputValue(inputCalculation.getInputValue())
                .currencyCodeFrom(inputCalculation.getCurrencyCodeFrom())
                .currencyCodeTo(inputCalculation.getCurrencyCodeTo())
                .rate(inputCalculation.getRate())
                .outputValue(inputCalculation.getOutputValue())
                .rateFrom(inputCalculation.getRateFrom())
                .build();
    }

    private final double inputValue;
    private final String currencyCodeFrom;
    private final String currencyCodeTo;
    private final double rate;
    private final double outputValue;
    private final String rateFrom;

}
