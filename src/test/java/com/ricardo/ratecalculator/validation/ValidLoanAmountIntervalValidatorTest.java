package com.ricardo.ratecalculator.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

public class ValidLoanAmountIntervalValidatorTest {

    @Test
    public void shouldReturnTrueWhenNumberIsInHundredsIncrement() {
        ValidLoanAmountIntervalValidator validLoanAmountInterval = new ValidLoanAmountIntervalValidator();

        assertThat(validLoanAmountInterval.isValid(new BigDecimal("1000"), null)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenNumberIsNotHundredsIncrement() {
        ValidLoanAmountIntervalValidator validLoanAmountInterval = new ValidLoanAmountIntervalValidator();

        assertThat(validLoanAmountInterval.isValid(new BigDecimal("12345"), null)).isFalse();
    }

}