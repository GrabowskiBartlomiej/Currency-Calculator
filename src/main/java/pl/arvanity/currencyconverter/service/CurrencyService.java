package pl.arvanity.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.model.Rates;
import pl.arvanity.currencyconverter.model.Table;
import pl.arvanity.currencyconverter.repository.CurrencyRepo;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {

    private RestTemplate restTemplate;
    private CurrencyRepo currencyRepo;

    public CurrencyService(RestTemplate restTemplate, CurrencyRepo currencyRepo) {
        this.restTemplate = restTemplate;
        this.currencyRepo = currencyRepo;
    }


    public void getAllCurrenciesFromAPI(){

        Table[] table = restTemplate.getForObject("http://api.nbp.pl/api/exchangerates/tables/A?format=json", Table[].class );
        List<Rates> rates = Arrays.asList(table[0].getCurrencies());
        for(Rates rate : rates){
            saveCurrencyInDatabase(new Currency(rate.getCurrency(), rate.getCode(), roundDouble(rate.getRate(),4), table[0].getEffectiveDate()));
        }
    }

    public void saveCurrencyInDatabase(Currency currency){
        currencyRepo.save(currency);
    }

    public void loadAllCurrencies(Model model){
        model.addAttribute("allCurrencies", currencyRepo.findAll());
        model.addAttribute("dateOfData", currencyRepo.findCurrencyById(1).getRateFrom());
    }

    public void getSingleCurrency(String currencyCode){

    }

    public String roundDouble(double rate, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(rate));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.toString();
    }

}
