package pl.arvanity.currencyconverter.controller.user.calculator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calculator")
public class CalculatorController {

    @GetMapping
    public String calculatorLandingPage(){
        return "calculator";
    }

    @GetMapping("new-calculation")
    public String addForm(){
        return "addForm";
    }
}
