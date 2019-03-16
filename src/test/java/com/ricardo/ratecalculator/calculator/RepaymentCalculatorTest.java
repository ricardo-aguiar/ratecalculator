package com.ricardo.ratecalculator.calculator;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.ricardo.ratecalculator.exception.UnableToCalculateRepaymentException;
import com.ricardo.ratecalculator.model.Lender;
import com.ricardo.ratecalculator.model.Repayment;
import com.ricardo.ratecalculator.model.builder.LenderBuilder;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RepaymentCalculatorTest {

    @Test
    @DisplayName("It should calculate monthly and total repayment")
    public void shouldCalculateMonthlyAndTotalRepayment() throws UnableToCalculateRepaymentException {
        RepaymentCalculator repaymentCalculator = new RepaymentCalculator(new InterestCalculator(), 36);
        Lender aLender = LenderBuilder.aLender().withLoanedFunds(1000).withInterestRate(0.07).build();

        Repayment actual = repaymentCalculator.calculateRepayment(1000, Collections.singletonList(aLender));

        assertThat(actual).isEqualToComparingFieldByField(new Repayment(30.78, 1108.08, 0.07));
    }

    @Test
    @DisplayName("It should throw an exception when the interest rate is 0.0 or less as it is not supported")
    public void shouldNotCalculateMonthlyAndTotalRepaymentForInterestRateZeroBelow() {
        RepaymentCalculator repaymentCalculator = new RepaymentCalculator(new InterestCalculator(), 36);
        Lender aLender = LenderBuilder.aLender().withLoanedFunds(1000).withInterestRate(0.0).build();

        Throwable actual = catchThrowable(() -> repaymentCalculator.calculateRepayment(1000, Collections.singletonList(aLender)));

        assertThat(actual).isInstanceOf(UnableToCalculateRepaymentException.class);
        assertThat(actual).hasMessage("Repayment calculation for interest free loan is not supported yet");
    }
}