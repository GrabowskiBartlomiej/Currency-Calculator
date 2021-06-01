package pl.arvanity.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.entity.ServiceCalls;
import pl.arvanity.currencyconverter.repository.CalculationRepo;
import pl.arvanity.currencyconverter.repository.ServiceCallsRepo;

import java.util.List;

@Service
public class CalculationService {

    private final CalculationRepo calculationRepo;
    private final CurrencyService currencyService;
    private final ServiceCallsRepo serviceCallsRepo;

    public CalculationService(CalculationRepo calculationRepo, CurrencyService currencyService, ServiceCallsRepo serviceCallsRepo) {
        this.calculationRepo = calculationRepo;
        this.currencyService = currencyService;
        this.serviceCallsRepo = serviceCallsRepo;
    }

    public Calculation convertCurrency(double inputMoney, String currencyCodeFrom, String currencyCodeTo) {
        Currency from = currencyService.getCurrencyByCode(currencyCodeFrom);
        Currency to = currencyService.getCurrencyByCode(currencyCodeTo);
        double rate = (from.getRate() / to.getRate());
        double outputMoney = rate * inputMoney;
        Calculation calculation = new Calculation(inputMoney, currencyCodeFrom, currencyCodeTo, rate, outputMoney, to.getRateFrom());
        calculationRepo.save(calculation);
        saveServiceCall(null, "POST", "Dodaj przeliczenie", calculation);
        return calculation;
    }


    public void getAllCalculations(Model model) {
        model.addAttribute("allCalculations", calculationRepo.findAll());
    }

    public List<Calculation> getAllCalculations() {
        saveServiceCall(null, "GET", "Pobierz listę przeliczeń", null);
        return calculationRepo.findAll();
    }

    public void deleteOnId(Long id) {
        saveServiceCall(id, "DELETE", "Usuń przeliczenie o id " + id, null);
        calculationRepo.delete(calculationRepo.findCalculationById(id));
    }

    public Calculation getSingleCalculationOnId(Long id) {
        return calculationRepo.findCalculationById(id);
    }

    public void saveServiceCall(Long id, String method, String action, Calculation calculation) {
        ServiceCalls serviceCalls = new ServiceCalls();
        serviceCalls.setMethod(method);
        serviceCalls.setAction(action);
        if (!(id == null)) {
            serviceCalls = fillServiceCallWithCalculationData(serviceCalls, getSingleCalculationOnId(id));
        }
        if (!(calculation == null)) {
            serviceCalls = fillServiceCallWithCalculationData(serviceCalls, calculation);
        }
        serviceCallsRepo.save(serviceCalls);
    }

    public List<ServiceCalls> getAllServiceCalls() {
        return serviceCallsRepo.findAll();
    }

    public ServiceCalls fillServiceCallWithCalculationData(ServiceCalls serviceCalls, Calculation calculation) {
        serviceCalls.setMoneyInput(calculation.getMoneyInput());
        serviceCalls.setCurrencyCodeFrom(calculation.getCurrencyCodeFrom());
        serviceCalls.setCurrencyCodeTo(calculation.getCurrencyCodeTo());
        serviceCalls.setMoneyOutput(calculation.getMoneyOutput());
        serviceCalls.setRate(calculation.getRate());
        serviceCalls.setRateFrom(calculation.getRateFrom());
        return serviceCalls;
    }

}
