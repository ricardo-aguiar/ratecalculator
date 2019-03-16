package com.ricardo.ratecalculator.calculator;

import static org.assertj.core.api.Assertions.assertThat;

import com.ricardo.ratecalculator.model.Lender;
import com.ricardo.ratecalculator.model.builder.LenderBuilder;
import java.util.Arrays;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class InterestCalculatorTest {

    @Test
    @DisplayName("It should calculate the average interest rate for a list of lenders and their loaned funds")
    public void shouldCalculateAverageInterestRate() {
        Lender bob = LenderBuilder.aLender().withInterestRate(0.080).withLoanedFunds(480).build();
        Lender smith = LenderBuilder.aLender().withInterestRate(0.071).withLoanedFunds(520).build();

        double actual = new InterestCalculator().calculateAverageInterest(Arrays.asList(bob, smith), 1000);

        assertThat(actual).isEqualTo(0.07532);
    }

    @Test
    @DisplayName("It should calculate monthly compound interest")
    public void shouldCalculateMonthlyCompoundInterest() {
        double actual = new InterestCalculator().calculateMonthlyCompoundInterest(0.07, 36);

        assertThat(actual).isCloseTo(0.03, Offset.offset(0.001));
    }

}