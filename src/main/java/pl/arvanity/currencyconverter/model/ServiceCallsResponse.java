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


    private String method;
    private String action;
    private double inputValue;
    private String currencyCodeFrom;
    private String currency;
    private String currencyCodeTo;
    private double outputValue;
    private double rate;
    private String rateFrom;
    private Date operationDate;

}
