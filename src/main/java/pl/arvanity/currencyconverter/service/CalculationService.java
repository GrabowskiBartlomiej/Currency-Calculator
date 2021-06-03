package pl.arvanity.currencyconverter.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.entity.Currency;
import pl.arvanity.currencyconverter.entity.ServiceCalls;
import pl.arvanity.currencyconverter.repository.CalculationRepo;
import pl.arvanity.currencyconverter.repository.ServiceCallsRepo;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class CalculationService {

    private final CalculationRepo calculationRepo;
    private final ServiceCallsRepo serviceCallsRepo;

    public CalculationService(CalculationRepo calculationRepo, ServiceCallsRepo serviceCallsRepo) {
        this.calculationRepo = calculationRepo;
        this.serviceCallsRepo = serviceCallsRepo;
    }


    public Calculation convertCurrency(double inputValue, Currency from, Currency to) {
        if (inputValue >= 0) {
            double rate = (from.getRate() / to.getRate());
            double outputMoney = round(rate * inputValue);

            Calculation calculation = new Calculation(inputValue, from.getCode(), to.getCode(), rate, outputMoney, to.getRateFrom());
            calculationRepo.save(calculation);
            saveServiceCall(calculation.getId(), "POST", "Add a calculation");
            return calculation;
        } else {
            return null;
        }
    }


    public void getAllCalculations(Model model) {
        model.addAttribute("allCalculations", calculationRepo.findAll());
    }


    public List<Calculation> getAllCalculations() {
        saveServiceCall(null, "GET", "Get the list of all calculations");
        return calculationRepo.findAll();
    }


    public void deleteOnId(Long id) {
        saveServiceCall(id, "DELETE", "Delete the calculation on id " + id);
        calculationRepo.delete(calculationRepo.findCalculationById(id));
    }


    public Calculation getSingleCalculationOnId(Long id) {
        return calculationRepo.findCalculationById(id);
    }


    public void saveServiceCall(Long calculationId, String method, String action) {
        ServiceCalls serviceCalls = new ServiceCalls();
        serviceCalls.setMethod(method);
        serviceCalls.setAction(action);
        if (!(calculationId == null)) {
            serviceCalls = fillServiceCallWithCalculationData(serviceCalls, getSingleCalculationOnId(calculationId));
        }
        serviceCallsRepo.save(serviceCalls);
    }


    public List<ServiceCalls> getAllServiceCalls() {
        saveServiceCall(null, "GET", "Get the list of all service calls so far");
        return serviceCallsRepo.findAll();
    }


    public ServiceCalls fillServiceCallWithCalculationData(ServiceCalls serviceCalls, Calculation calculation) {
        serviceCalls.setInputValue(calculation.getInputValue());
        serviceCalls.setCurrencyCodeFrom(calculation.getCurrencyCodeFrom());
        serviceCalls.setCurrencyCodeTo(calculation.getCurrencyCodeTo());
        serviceCalls.setOutputValue(calculation.getOutputValue());
        serviceCalls.setRate(calculation.getRate());
        serviceCalls.setRateFrom(calculation.getRateFrom());
        return serviceCalls;
    }


    private double round(double outputMoney) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String out = df.format(outputMoney);
        out = out.replace(",", ".");
        return Double.parseDouble(out);
    }


}
