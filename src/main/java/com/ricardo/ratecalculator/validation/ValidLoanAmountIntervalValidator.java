package com.ricardo.ratecalculator.validation;

import com.ricardo.ratecalculator.validation.annotation.ValidLoanAmountInterval;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidLoanAmountIntervalValidator implements ConstraintValidator<ValidLoanAmountInterval, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value % 100 == 0;

    }
}
