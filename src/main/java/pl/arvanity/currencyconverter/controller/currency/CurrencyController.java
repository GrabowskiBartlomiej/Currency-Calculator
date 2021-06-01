package pl.arvanity.currencyconverter.controller.currency;

import org.springframework.web.bind.annotation.*;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.entity.ServiceCalls;
import pl.arvanity.currencyconverter.service.CalculationService;
import pl.arvanity.currencyconverter.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("currencies")
public class CurrencyController {

    private final CurrencyService currencyService;
    private final CalculationService calculationService;

    public CurrencyController(CurrencyService currencyService, CalculationService calculationService) {
        this.currencyService = currencyService;
        this.calculationService = calculationService;
    }

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAllCurrencies();
    }


    @PostMapping("{inputMoney}/{currencyCodeFrom}/{currencyCodeTo}")
    public Calculation convert(@PathVariable double inputMoney, @PathVariable String currencyCodeFrom, @PathVariable String currencyCodeTo) {
        return calculationService.convertCurrency(inputMoney, currencyService.getCurrencyByCode(currencyCodeFrom), currencyService.getCurrencyByCode(currencyCodeTo));
    }


    @GetMapping("rates/{codes}")
    public List<Currency> getCurrenciesByCodes(@PathVariable String codes){
        return currencyService.getCurrenciesByCodes(codes);
    }


    @GetMapping("service-calls")
    public List<ServiceCalls> getAllServiceCalls(){
        return calculationService.getAllServiceCalls();
    }

}
