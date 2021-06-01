package pl.arvanity.currencyconverter.controller.currency;

import org.springframework.web.bind.annotation.*;
import pl.arvanity.currencyconverter.entity.Calculation;
import pl.arvanity.currencyconverter.service.CalculationService;

import java.util.List;

@RestController
@RequestMapping("currencies/calculations")
public class CalculationsController {

    private final CalculationService calculationService;

    public CalculationsController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @GetMapping
    public List<Calculation> getAllCalculations(){
        return calculationService.getAllCalculations();
    }

    @DeleteMapping("{id}")
    public List<Calculation> deleteOnId(@PathVariable Long id){
        calculationService.deleteOnId(id);
        return calculationService.getAllCalculations();
    }

}
