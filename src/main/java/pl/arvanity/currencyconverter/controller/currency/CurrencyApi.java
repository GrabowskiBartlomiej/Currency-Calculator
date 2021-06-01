package pl.arvanity.currencyconverter.controller.currency;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.entity.ServiceCalls;
import pl.arvanity.currencyconverter.service.CalculationService;
import pl.arvanity.currencyconverter.service.CurrencyService;

import java.util.List;

@RestController
@RequestMapping("currencies")
public class CurrencyApi {

    private final CurrencyService currencyService;
    private final CalculationService calculationService;

    public CurrencyApi(CurrencyService currencyService, CalculationService calculationService) {
        this.currencyService = currencyService;
        this.calculationService = calculationService;
    }


    @ApiOperation(value = "Lista wszystkich kursów z NBP API")
    @GetMapping
    public List<Currency> getAll() {
        return currencyService.getAllCurrencies();
    }


    @ApiOperation(value = "Przelicz daną kwotę z waluty A na walutę B", notes = "Oddzielaj wartości dzięki \"/\" , podawaj je w następującej kolejności = kwota, waluta aktualna, waluta docelowa, waluty w formie kodu")
    @PostMapping("{inputMoney}/{currencyCodeFrom}/{currencyCodeTo}")
    public Calculation convert(@ApiParam(value = "kwota", example = "1000") @PathVariable double inputMoney, @ApiParam(value = "kod waluty", example = "eur") @PathVariable String currencyCodeFrom, @ApiParam(value = "kod waluty docelowej", example = "pln") @PathVariable String currencyCodeTo) {
        return calculationService.convertCurrency(inputMoney, currencyService.getCurrencyByCode(currencyCodeFrom), currencyService.getCurrencyByCode(currencyCodeTo));
    }


    @ApiOperation(value = "Lista wszystkich wykonanych przeliczeń")
    @GetMapping("calculations")
    public List<Calculation> getAllCalculations() {
        return calculationService.getAllCalculations();
    }


    @ApiOperation(value = "Usuń wybrane po id przeliczenie", notes = "po sukcesie, przekierowywuje na listę wszystkich pozostałych przeliczeń")
    @DeleteMapping("calculations/{id}")
    public List<Calculation> deleteOnId(@ApiParam(value = "unikatowy numer id", example = "1") @PathVariable Long id) {
        calculationService.deleteOnId(id);
        return calculationService.getAllCalculations();
    }


    @ApiOperation(value = "Lista kursów wybranych przez kod kursu", notes = "Oddzielaj kody za pomoca \"&\"")
    @GetMapping("rates/{codes}")
    public List<Currency> getCurrenciesByCodes(@ApiParam(value = "unikatowy trzyliterowy kod kursu", example = "pln&eur&gbp") @PathVariable String codes) {
        return currencyService.getCurrenciesByCodes(codes);
    }


    @ApiOperation(value = "Lista wszystkich dotychczasowych operacji wykonanych na serwisie", notes = "zarejestrowane jest tutaj każde wywołanie serwisu")
    @GetMapping("service-calls")
    public List<ServiceCalls> getAllServiceCalls() {
        return calculationService.getAllServiceCalls();
    }

}
