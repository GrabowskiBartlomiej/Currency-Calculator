package pl.arvanity.currencyconverter.model;

import lombok.Builder;
import lombok.Getter;
import pl.arvanity.currencyconverter.entity.Calculation;

@Getter
@Builder
public class CalculationResponse {

    public static CalculationResponse of(Calculation inputCalculation){
        return CalculationResponse.builder()
                .inputValue(inputCalculation.getInputValue())
                .currencyCodeFrom(inputCalculation.getCurrencyCodeFrom())
                .currencyCodeTo(inputCalculation.getCurrencyCodeTo())
                .rate(inputCalculation.getRate())
                .outputValue(inputCalculation.getOutputValue())
                .rateFrom(inputCalculation.getRateFrom())
                .build();
    }

    private double inputValue;
    private String currencyCodeFrom;
    private String currencyCodeTo;
    private double rate;
    private double outputValue;
    private String rateFrom;

}
