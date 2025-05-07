package com.likelion.session.dto.calculator.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CalculatorAddRequestDTO {
    private int number1;
    private int number2;
}
