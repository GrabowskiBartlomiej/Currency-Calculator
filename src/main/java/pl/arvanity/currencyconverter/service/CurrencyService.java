package pl.arvanity.currencyconverter.service;

import org.springframework.scheduling.annotation.Scheduled;
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
        calculationService.saveServiceCall(null, "GET", "Pobieranie listy wszystkich kursów z bazy danych", null);
        return currencyRepo.findAll();
    }


    public void loadAllCurrencies(Model model) {
        model.addAttribute("allCurrencies", currencyRepo.findAll());
        model.addAttribute("dateOfData", currencyRepo.findCurrencyById(1).getRateFrom());
    }


    @PostConstruct
    @Scheduled(cron = "0 0 13 * * *")
    public void getAllCurrenciesFromApi() {
        Table[] table = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/A?format=json", Table[].class);
        List<Rates> rates = Arrays.asList(table[0].getCurrencies());
        List<Currency> currencies = currencyRepo.findAll();
        for (Rates rate : rates) {
            if (currencies.isEmpty()) {
                saveCurrencyInDatabase(new Currency(rate.getCurrency(), rate.getCode(), rate.getRate(), table[0].getEffectiveDate()));
            } else {
                Currency currency = getCurrencyByCode(rate.getCode());
                if (!(currency == null)) {
                    currency.setRate(rate.getRate());
                    currency.setRateFrom(table[0].getEffectiveDate());
                }
            }
        }
        saveCurrencyInDatabase(new Currency("Polski złoty", "PLN", 1, table[0].getEffectiveDate()));
        calculationService.saveServiceCall(null, "GET", "Pobranie wszystkich kursów z NBP API i zapisywanie do bazy danych", null);
    }


    public List<Currency> getCurrenciesByCodes(String codes) {
        String description = "";
        codes = codes.toUpperCase();
        List<String> singleCodes = Arrays.asList(codes.split(","));
        List<Currency> currencies = new ArrayList<>();
        for (String code : singleCodes) {
            if (!(getCurrencyByCode(code) == null)) {
                currencies.add(getCurrencyByCode(code));
                description += code + ", ";
            }
        }
        calculationService.saveServiceCall(null, "GET", "Pobieranie kursów dla " + description, null);
        return currencies;
    }


}
