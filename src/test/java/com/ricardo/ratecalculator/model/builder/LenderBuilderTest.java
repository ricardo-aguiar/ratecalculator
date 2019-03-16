package com.ricardo.ratecalculator.model.builder;

import static org.assertj.core.api.Assertions.assertThat;

import com.ricardo.ratecalculator.model.Lender;
import org.junit.jupiter.api.Test;

class LenderBuilderTest {

    @Test
    public void shouldBuildALenderObject() {
        Lender actual = LenderBuilder.aLender().withName("Bob").withAvailableFunds(1000).withInterestRate(0.1).withLoanedFunds(1).build();

        assertThat(actual).isEqualTo(new Lender("Bob", 0.1, 1000, 1));
    }

    @Test
    public void shouldBuildALenderFromAnotherLender() {
        Lender original = new Lender("Bob", 0.1, 1000, 1);

        Lender actual = LenderBuilder.copy(original).build();

        assertThat(actual).isEqualTo(original);
    }

}