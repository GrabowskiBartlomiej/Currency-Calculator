package pl.arvanity.currencyconverter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.arvanity.currencyconverter.service.CurrencyService;

@Controller
@RequestMapping("/")
public class HomePageController {

    private CurrencyService currencyService;

    public HomePageController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping
    public String homePage(Model model){
        currencyService.getAllCurrenciesFromAPI();
        currencyService.loadAllCurrencies(model);
        return "index";
    }

}
