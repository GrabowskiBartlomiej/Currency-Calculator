package pl.arvanity.currencyconverter.controller.currency;

import org.springframework.web.bind.annotation.*;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.entity.ServiceCalls;
import pl.arvanity.currencyconverter.service.CalculationService;
import pl.arvanity.currencyconverter.service.CurrencyService;

import java.util.ArrayList;
import java.util.Arrays;
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

    @GetMapping("{id}")
    public Currency getOne(@PathVariable long id) {
        return currencyService.getSingleCurrency(id);
    }

    @GetMapping("rates/{codes}")
    public List<Currency> getRatesByCodes(@PathVariable String codes){
        codes = codes.toUpperCase();
        List<String> singleCodes = Arrays.asList(codes.split("&"));
        List<Currency> currencies = new ArrayList<>();
        for(String code : singleCodes){
            currencies.add(currencyService.getCurrencyByCode(code));
        }
        return currencies;
    }

    @PostMapping("{inputMoney}/{currencyCodeFrom}/{currencyCodeTo}")
    public Calculation convert(@PathVariable double inputMoney, @PathVariable String currencyCodeFrom, @PathVariable String currencyCodeTo) {
        return calculationService.convertCurrency(inputMoney, currencyCodeFrom.toUpperCase(), currencyCodeTo.toUpperCase());
    }

    @GetMapping("calculations")
    public List<Calculation> getAllCalculations(){
        return calculationService.getAllCalculations();
    }

     @DeleteMapping("calculations/{id}")
    public List<Calculation> deleteOnId(@PathVariable Long id){
        calculationService.deleteOnId(id);
        return calculationService.getAllCalculations();
    }

    @GetMapping("service-calls")
    public List<ServiceCalls> getAllServiceCalls(){
        return calculationService.getAllServiceCalls();
    }

}
