package com.ricardo.ratecalculator.validation;

import com.ricardo.ratecalculator.validation.annotation.ValidLoanAmountInterval;
import java.math.BigDecimal;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidLoanAmountIntervalValidator implements ConstraintValidator<ValidLoanAmountInterval, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return value.remainder(new BigDecimal("100")).compareTo(new BigDecimal("0")) == 0;

    }
}
