package com.ricardo.ratecalculator.service;

import com.ricardo.ratecalculator.model.Lender;
import com.ricardo.ratecalculator.model.builder.LenderBuilder;
import com.ricardo.ratecalculator.repository.DataRepository;
import java.math.BigDecimal;
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
    public boolean doesNotHaveSufficientFundsInMarket(BigDecimal loanAmountRequest) {
        BigDecimal availableMarketFund = this.dataRepository.findAll()
                                                            .stream()
                                                            .map(Lender::getAvailableFunds)
                                                            .reduce(new BigDecimal("0"), BigDecimal::add);
        return availableMarketFund.compareTo(loanAmountRequest) < 0;
    }

    /**
     * Given a loan amount request, this method elect lenders, with the lowest interest rate in the market, to fulfill a loan request.
     *
     * @param loanAmountRequest - The loan request amount
     * @return A list elected of {@link Lender}
     */
    public List<Lender> electOffers(BigDecimal loanAmountRequest) {
        Iterator<Lender> lendersIterator = this.dataRepository.findAllOrderedByInterestRateAsc().iterator();

        List<Lender> electedLenders = new ArrayList<>();
        BigDecimal remainingAmountNeeded = loanAmountRequest;
        while (lendersIterator.hasNext() && remainingAmountNeeded.compareTo(new BigDecimal("0")) > 0) {
            Lender lender = lendersIterator.next();
            BigDecimal loanedFund = lender.getAvailableFunds().compareTo(remainingAmountNeeded) < 0 ? lender.getAvailableFunds()
                                                                                                    : remainingAmountNeeded;
            remainingAmountNeeded = remainingAmountNeeded.subtract(loanedFund);
            electedLenders.add(LenderBuilder.copy(lender)
                                            .withLoanedFunds(loanedFund)
                                            .build());
        }
        return Collections.unmodifiableList(electedLenders);
    }
}
