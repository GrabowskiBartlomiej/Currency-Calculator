package pl.arvanity.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.model.Rates;
import pl.arvanity.currencyconverter.model.Table;
import pl.arvanity.currencyconverter.repository.CurrencyRepo;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepo currencyRepo;
    private final CalculationService calculationService;

    public CurrencyService(RestTemplate restTemplate, CurrencyRepo currencyRepo, CalculationService calculationService) {
        this.restTemplate = restTemplate;
        this.currencyRepo = currencyRepo;
        this.calculationService = calculationService;
    }

    public void saveCurrencyInDatabase(Currency currency) {
        currencyRepo.save(currency);
    }


    public Currency getCurrencyByCode(String currencyCodeFrom) {
        return currencyRepo.findCurrencyByCode(currencyCodeFrom);
    }


    public List<Currency> getAllCurrencies() {
        calculationService.saveServiceCall(null, "GET", "Pobierz listę wszystkich kursów", null);
        return currencyRepo.findAll();
    }


    public void loadAllCurrencies(Model model) {
        model.addAttribute("allCurrencies", currencyRepo.findAll());
        model.addAttribute("dateOfData", currencyRepo.findCurrencyById(1).getRateFrom());
    }

    @PostConstruct
    public void getAllCurrenciesFromApi() {
        Table[] table = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/A?format=json", Table[].class);
        List<Rates> rates = Arrays.asList(table[0].getCurrencies());
        for (Rates rate : rates) {
            saveCurrencyInDatabase(new Currency(rate.getCurrency(), rate.getCode(), rate.getRate(), table[0].getEffectiveDate()));
        }
        saveCurrencyInDatabase(new Currency("Polski złoty", "PLN", 1, table[0].getEffectiveDate()));
    }


    public List<Currency> getCurrenciesByCodes(String codes) {
        String description = "";
        codes = codes.toUpperCase();
        List<String> singleCodes = Arrays.asList(codes.split("&"));
        List<Currency> currencies = new ArrayList<>();
        for(String code : singleCodes){
            currencies.add(getCurrencyByCode(code));
            if(!(getCurrencyByCode(code) == null)){
                description += code + ", ";
            }
        }
        calculationService.saveServiceCall(null,"GET", "Pobierz kursy dla " + description, null);
        return currencies;
    }
}
