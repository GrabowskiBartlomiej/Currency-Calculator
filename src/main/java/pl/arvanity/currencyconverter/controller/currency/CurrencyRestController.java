package pl.arvanity.currencyconverter.controller.currency;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.model.CalculationResponse;
import pl.arvanity.currencyconverter.model.CurrencyResponse;
import pl.arvanity.currencyconverter.model.ServiceCallsResponse;
import pl.arvanity.currencyconverter.service.CalculationService;
import pl.arvanity.currencyconverter.service.CurrencyService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("currencies")
public class CurrencyRestController {

    private final CurrencyService currencyService;
    private final CalculationService calculationService;

    public CurrencyRestController(CurrencyService currencyService, CalculationService calculationService) {
        this.currencyService = currencyService;
        this.calculationService = calculationService;
    }


    @ApiOperation(value = "List of all currencies from NBP API")
    @GetMapping
    public List<CurrencyResponse> getAll(HttpServletRequest req) throws InterruptedException {

        String delayHeader = req.getHeader("delay");

        System.out.println(System.getenv("HEADER"));

        if (delayHeader != null) {
            Long delay = Long.parseLong(delayHeader);
            if (delay >= 0) {
                Thread.sleep(delay);
            }
        }

        return currencyService.getAllCurrencies().stream()
                .map(c -> CurrencyResponse.of(c))
                .collect(Collectors.toList());
    }


    /*returning object only for purpose of showing user-friendly messages*/
    @ApiOperation(value = "Convert the money from currency A to currency B",
            notes = "The code should link as follows -> http://localhost:8080/currencies/convert?currencyCodeFrom=eur&currencyCodeTo=pln&inputMoney=1000")
    @PostMapping("/convert")
    public Object convert(@ApiParam(value = "Input", example = "333") @RequestParam double inputMoney,
                          @ApiParam(value = "Currency code", example = "eur") @RequestParam String currencyCodeFrom,
                          @ApiParam(value = "Target currency code", example = "pln") @RequestParam String currencyCodeTo) {
        try {
            Calculation c = calculationService.convertCurrency(inputMoney, currencyService.getCurrencyByCode(currencyCodeFrom), currencyService.getCurrencyByCode(currencyCodeTo));
            if (c == null) {
                return "Input has to be equal to 0 or higher";
            } else {
                return CalculationResponse.of(c);
            }
        } catch (Exception e) {
            return "Data you used are not correct";
        }
    }


    @ApiOperation(value = "List of all calculations made")
    @GetMapping("calculations")
    public List<CalculationResponse> getAllCalculations() {
        return calculationService.getAllCalculations().stream()
                .map(c -> CalculationResponse.of(c))
                .collect(Collectors.toList());
    }


    @ApiOperation(value = "Delete calculation, selected by id ", notes = "on success displays list of remaining calculations")
    @DeleteMapping("calculations/{id}")
    public String deleteOnId(@ApiParam(value = "unique value id", example = "1") @PathVariable Long id) {
        try {
            calculationService.deleteOnId(id);
            return "Calculation with id " + id + " has been deleted.";
        } catch (NullPointerException e) {
            return "There is no calculation with such id.";
        }
    }


    @ApiOperation(value = "List of currencies selected by currency code", notes = "Separate the codes by \",\"")
    @GetMapping("rates/{codes}")
    public List<CurrencyResponse> getCurrenciesByCodes(@ApiParam(value = "unique three-letter currency code ", example = "pln,eur,gbp") @PathVariable String codes) {
        return currencyService.getCurrenciesByCodes(codes).stream()
                .map(c -> CurrencyResponse.of(c))
                .collect(Collectors.toList());
    }


    @ApiOperation(value = "List of all operations on the service so far", notes = "Here are registered all operations")
    @GetMapping("service-calls")
    public List<ServiceCallsResponse> getAllServiceCalls() {
        return calculationService.getAllServiceCalls().stream()
                .map(s -> ServiceCallsResponse.of(s))
                .collect(Collectors.toList());
    }


}
