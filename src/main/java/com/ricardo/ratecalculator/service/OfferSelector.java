package com.ricardo.ratecalculator.service;

import com.ricardo.ratecalculator.model.Lender;
import com.ricardo.ratecalculator.model.builder.LenderBuilder;
import com.ricardo.ratecalculator.repository.DataRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class OfferSelector {

    private final DataRepository dataRepository;

    public OfferSelector(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    /**
     * Verifies if the market has sufficient funds in the market to fulfill a loan request.
     *
     * @return {@code true} if it does NOT have sufficient funds, otherwise, {@code false}
     */
    public boolean doesNotHaveSufficientFundsInMarket(int loanAmountRequest) {
        int availableMarketFund = this.dataRepository.findAll()
                                                     .stream()
                                                     .mapToInt(Lender::getAvailableFunds)
                                                     .reduce(0, Integer::sum);
        return availableMarketFund < loanAmountRequest;
    }

    /**
     * Given a loan amount request, this method elect lenders, with the lowest interest rate in the market, to fulfill a loan request.
     *
     * @param loanAmountRequest - The loan request amount
     * @return A list elected of {@link Lender}
     */
    public List<Lender> electOffers(int loanAmountRequest) {
        Iterator<Lender> lendersIterator = this.dataRepository.findAllOrderedByInterestRateAsc().iterator();

        List<Lender> electedLenders = new ArrayList<>();
        int remainingAmountNeeded = loanAmountRequest;
        while (lendersIterator.hasNext() && remainingAmountNeeded > 0) {
            Lender lender = lendersIterator.next();
            int loanedFund = lender.getAvailableFunds() < remainingAmountNeeded ? lender.getAvailableFunds()
                                                                                : remainingAmountNeeded;
            remainingAmountNeeded -= loanedFund;
            electedLenders.add(LenderBuilder.copy(lender)
                                            .withLoanedFunds(loanedFund)
                                            .build());
        }
        return Collections.unmodifiableList(electedLenders);
    }
}
