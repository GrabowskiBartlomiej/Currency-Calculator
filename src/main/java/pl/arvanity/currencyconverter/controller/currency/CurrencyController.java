package pl.arvanity.currencyconverter.controller.currency;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.entity.Currency;
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

    @GetMapping("{inputMoney}/{currencyCodeFrom}/{currencyCodeTo}")
    public Calculation convert(@PathVariable double inputMoney, @PathVariable String currencyCodeFrom, @PathVariable String currencyCodeTo) {
        return calculationService.convertCurrency(inputMoney, currencyCodeFrom.toUpperCase(), currencyCodeTo.toUpperCase());
    }

    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAllCurrencies();
    }

    @GetMapping("/{id}")
    public Currency getOne(@PathVariable long id) {
        return currencyService.getSingleCurrency(id);
    }


}
