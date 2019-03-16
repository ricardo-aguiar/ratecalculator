package com.ricardo.ratecalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ricardo.ratecalculator.model.Lender;
import com.ricardo.ratecalculator.model.builder.LenderBuilder;
import com.ricardo.ratecalculator.repository.DataRepository;
import com.ricardo.ratecalculator.repository.InMemLenderDataRepository;
import com.ricardo.ratecalculator.service.OfferSelector;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class OfferSelectorTest {

    private DataRepository mockDataRepo;
    private OfferSelector underTest;

    @BeforeEach
    void setUp() {
        mockDataRepo = mock(InMemLenderDataRepository.class);
        underTest = new OfferSelector(mockDataRepo);
    }

    @Test
    @DisplayName("It should select a lender with the lowest interest rate to fulfill a loan request")
    public void shouldSelectLenderWithLowestInterestFee() {
        Lender bob = LenderBuilder.aLender().withName("Bob").withInterestRate(0.069).withAvailableFunds(640).build();
        Lender smith = LenderBuilder.aLender().withName("Smith").withInterestRate(0.075).withAvailableFunds(1000).build();

        when(mockDataRepo.findAllOrderedByInterestRateAsc()).thenReturn(Arrays.asList(bob, smith));

        List<Lender> actual = this.underTest.electOffers(340);
        assertThat(actual).isNotEmpty();
        assertThat(actual).extracting(Lender::getName, Lender::getInterestRate, Lender::getAvailableFunds, Lender::getLoanedFunds)
                                .containsOnly(tuple("Bob", 0.069, 640, 340));
    }

    @Test
    @DisplayName("It should select enough lenders with the lowest interest rate  to fulfill a loan request")
    public void shouldSelectEnoutLendersFromPoolToFulfillRequestedLoandAmount() {
        Lender bob = LenderBuilder.aLender().withName("Bob").withInterestRate(0.069).withAvailableFunds(640).build();
        Lender smith = LenderBuilder.aLender().withName("Smith").withInterestRate(0.071).withAvailableFunds(1000).build();
        Lender john = LenderBuilder.aLender().withName("john").withInterestRate(0.075).withAvailableFunds(1000).build();

        when(mockDataRepo.findAllOrderedByInterestRateAsc()).thenReturn(Arrays.asList(bob, smith, john));

        List<Lender> actual = this.underTest.electOffers(840);

        assertThat(actual).isNotEmpty();
        assertThat(actual).extracting(Lender::getName, Lender::getInterestRate, Lender::getAvailableFunds, Lender::getLoanedFunds)
                          .containsOnly(tuple("Bob", 0.069, 640, 640),
                                        tuple("Smith", 0.071, 1000, 200));
    }

    @Test
    @DisplayName("It should return an empty list if there are not lender offers in the market")
    public void shouldReturnAnEmptyListIfThereAreNoLenders() {

        when(mockDataRepo.findAllOrderedByInterestRateAsc()).thenReturn(Collections.emptyList());

        List<Lender> actual = this.underTest.electOffers(840);

        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("It should return false if there are sufficient funds in the market to fulfill a loan request")
    public void shouldReturnFalseWhenThereIsSufficientFundsToFulfillLoanRequest() {
        Lender bob = LenderBuilder.aLender().withName("Bob").withInterestRate(0.069).withAvailableFunds(640).build();
        Lender smith = LenderBuilder.aLender().withName("Smith").withInterestRate(0.071).withAvailableFunds(1000).build();
        when(mockDataRepo.findAll()).thenReturn(Arrays.asList(bob, smith));

        boolean actual = this.underTest.doesNotHaveSufficientFundsInMarket(1000);

        assertThat(actual).isFalse();
    }


    @Test
    @DisplayName("It should return true if there aren't sufficient funds in the market to fulfill a loan request")
    public void shouldReturnTrueWhenThereIsSufficientFundsToFulfillLoanRequest() {
        Lender bob = LenderBuilder.aLender().withName("Bob").withInterestRate(0.069).withAvailableFunds(640).build();
        when(mockDataRepo.findAll()).thenReturn(Arrays.asList(bob));

        boolean actual = this.underTest.doesNotHaveSufficientFundsInMarket(1000);

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("It should return true if there aren't lenders offers in the market")
    public void shouldReturnTrueIfThereAreNoLendersOfferInTheMarket() {
        when(mockDataRepo.findAll()).thenReturn(Collections.emptyList());

        boolean actual = this.underTest.doesNotHaveSufficientFundsInMarket(1000);

        assertThat(actual).isTrue();
    }





}