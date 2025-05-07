package com.likelion.session.controller.calculator;

import com.likelion.session.dto.calculator.request.CalculatorAddRequestDTO;
import com.likelion.session.dto.calculator.request.CalculatorMultiplyRequest;
import com.likelion.session.service.calculator.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class CalculatorController {

    public final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public int addTwoNumbers(@RequestBody CalculatorAddRequestDTO request) {
        log.info(String.valueOf(request.getNumber1()));
        return calculatorService.add(request.getNumber1(), request.getNumber2());
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return calculatorService.multiply(request.getNumber1(), request.getNumber2());
    }
}
