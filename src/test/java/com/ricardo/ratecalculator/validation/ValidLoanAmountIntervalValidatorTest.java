package com.ricardo.ratecalculator.validation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class ValidLoanAmountIntervalValidatorTest {

    @Test
    public void shouldReturnTrueWhenNumberIsInHundredsIncrement() {
        ValidLoanAmountIntervalValidator validLoanAmountInterval = new ValidLoanAmountIntervalValidator();

        assertThat(validLoanAmountInterval.isValid(1000, null)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenNumberIsNotHundredsIncrement() {
        ValidLoanAmountIntervalValidator validLoanAmountInterval = new ValidLoanAmountIntervalValidator();

        assertThat(validLoanAmountInterval.isValid(12345, null)).isFalse();
    }

}