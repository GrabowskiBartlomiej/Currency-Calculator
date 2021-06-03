package pl.arvanity.currencyconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import pl.arvanity.currencyconverter.entity.ServiceCalls;

import java.util.Date;


@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Getter
@Builder
public class ServiceCallsResponse {

    public static ServiceCallsResponse of(ServiceCalls serviceCalls) {
        return ServiceCallsResponse.builder()
                .method(serviceCalls.getMethod())
                .action(serviceCalls.getAction())
                .inputValue(serviceCalls.getInputValue())
                .currencyCodeFrom(serviceCalls.getCurrencyCodeFrom())
                .currency(serviceCalls.getCurrency())
                .currencyCodeTo(serviceCalls.getCurrencyCodeTo())
                .outputValue(serviceCalls.getOutputValue())
                .rate(serviceCalls.getRate())
                .rateFrom(serviceCalls.getRateFrom())
                .operationDate(serviceCalls.getOperationDate())
                .build();
    }


    private final String method;
    private final String action;
    private final double inputValue;
    private final String currencyCodeFrom;
    private final String currency;
    private final String currencyCodeTo;
    private final double outputValue;
    private final double rate;
    private final String rateFrom;
    private final Date operationDate;

}
