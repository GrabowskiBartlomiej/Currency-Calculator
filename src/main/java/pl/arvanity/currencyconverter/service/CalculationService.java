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

    public Calculation convertCurrency(double inputMoney, Currency from, Currency to) {
        double rate = (from.getRate() / to.getRate());
        double outputMoney = rate * inputMoney;
        DecimalFormat df = new DecimalFormat("#.##");
        df.setMinimumFractionDigits(2);
        String out = df.format(outputMoney);
        out = out.replace(",", ".");
        System.out.println(out);
        outputMoney = Double.parseDouble(out);
        System.out.println(outputMoney);

        Calculation calculation = new Calculation(inputMoney, from.getCode(), to.getCode(), rate, outputMoney, to.getRateFrom());
        calculationRepo.save(calculation);
        saveServiceCall(null, "POST", "Add a calculation", calculation);
        return calculation;
    }


    public void getAllCalculations(Model model) {
        model.addAttribute("allCalculations", calculationRepo.findAll());
    }

    public List<Calculation> getAllCalculations() {
        saveServiceCall(null, "GET", "Get the list of all calculations", null);
        return calculationRepo.findAll();
    }

    public void deleteOnId(Long id) {
        Calculation calculation = calculationRepo.findCalculationById(id);
        if (!(calculation == null)) {
            saveServiceCall(id, "DELETE", "Delete the calculation on id " + id, null);
            calculationRepo.delete(calculationRepo.findCalculationById(id));
        }
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
        saveServiceCall(null, "GET", "Get the list of all service calls so far", null);
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

}
