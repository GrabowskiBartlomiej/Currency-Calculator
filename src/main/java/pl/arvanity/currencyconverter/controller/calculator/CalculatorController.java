package pl.arvanity.currencyconverter.controller.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.arvanity.currencyconverter.service.CalculationService;
import pl.arvanity.currencyconverter.service.CurrencyService;


@Controller
@RequestMapping("calculator")
public class CalculatorController {

    private final CurrencyService currencyService;
    private final CalculationService calculationService;

    public CalculatorController(CurrencyService currencyService, CalculationService calculationService) {
        this.currencyService = currencyService;
        this.calculationService = calculationService;
    }

    @GetMapping
    public String calculatorLandingPage() {
        return "calculator";
    }

    @GetMapping("new-calculation")
    public String addForm(Model model) {
        currencyService.loadAllCurrencies(model);
        return "addForm";
    }

    @PostMapping("new-calculation")
    public String addSuccess(Model model, @RequestParam double inputValue, @RequestParam String from, @RequestParam String to) {
        model.addAttribute("calculation", calculationService.convertCurrency(inputValue, currencyService.getCurrencyByCode(from), currencyService.getCurrencyByCode(to)));
        return "summary";
    }

    @GetMapping("all-calculations")
    public String getAll(Model model) {
        calculationService.getAllCalculations(model);
        return "allCalculations";
    }

}
