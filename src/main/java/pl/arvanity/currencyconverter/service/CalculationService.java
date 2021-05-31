package pl.arvanity.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.repository.CalculationRepo;

@Service
public class CalculationService {

    private final CalculationRepo calculationRepo;
    private final CurrencyService currencyService;

    public CalculationService(CalculationRepo calculationRepo, CurrencyService currencyService) {
        this.calculationRepo = calculationRepo;
        this.currencyService = currencyService;
    }

    public Calculation convertCurrency(double inputMoney, String currencyCodeFrom, String currencyCodeTo) {
        Currency from = currencyService.getCurrencyByCode(currencyCodeFrom);
        Currency to = currencyService.getCurrencyByCode(currencyCodeTo);
        double rate = (from.getRate() / to.getRate());
        double outputMoney = rate * inputMoney;
        Calculation calculation = new Calculation(inputMoney, currencyCodeFrom, currencyCodeTo, rate, outputMoney, to.getRateFrom());
        calculationRepo.save(calculation);
        return calculation;
    }


    public void getAllCalculations(Model model) {
        model.addAttribute("allCalculations", calculationRepo.findAll());
    }
}
