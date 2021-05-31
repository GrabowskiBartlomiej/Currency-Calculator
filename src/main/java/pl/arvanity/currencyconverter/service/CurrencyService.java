package pl.arvanity.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.model.Rates;
import pl.arvanity.currencyconverter.model.Table;
import pl.arvanity.currencyconverter.repository.CurrencyRepo;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    private final RestTemplate restTemplate;
    private final CurrencyRepo currencyRepo;

    public CurrencyService(RestTemplate restTemplate, CurrencyRepo currencyRepo) {
        this.restTemplate = restTemplate;
        this.currencyRepo = currencyRepo;
    }


    public void saveCurrencyInDatabase(Currency currency) {
        currencyRepo.save(currency);
    }


    public Currency getSingleCurrency(Long id) {
        return currencyRepo.findCurrencyById(id);
    }


    public Currency getCurrencyByCode(String currencyCodeFrom) {
        return currencyRepo.findCurrencyByCode(currencyCodeFrom);
    }


    public List<Currency> getAllCurrencies() {
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


}
