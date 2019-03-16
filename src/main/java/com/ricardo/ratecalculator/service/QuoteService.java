package com.ricardo.ratecalculator.service;

import com.ricardo.ratecalculator.calculator.RepaymentCalculator;
import com.ricardo.ratecalculator.exception.UnableToCalculateRepaymentException;
import com.ricardo.ratecalculator.model.Lender;
import com.ricardo.ratecalculator.model.Quote;
import com.ricardo.ratecalculator.model.Repayment;
import java.util.List;
import java.util.Optional;

public class QuoteService {

    private final OfferSelector offerSelector;
    private final RepaymentCalculator repaymentCalculator;

    public QuoteService(OfferSelector offerSelector,
                        RepaymentCalculator repaymentCalculator) {

        this.offerSelector = offerSelector;
        this.repaymentCalculator = repaymentCalculator;
    }

    /**
     * Orchestrate selection of lowest offers to fulfill loan request and calculate a quotation
     * @param loanAmountRequested
     * @return A {@link Quote} if there are enough fund in the market, ortherwise {@link Optional#empty()}
     * @throws UnableToCalculateRepaymentException for interest free offers
     */
    public Optional<Quote> getQuote(int loanAmountRequested) throws UnableToCalculateRepaymentException {
        if (offerSelector.doesNotHaveSufficientFundsInMarket(loanAmountRequested)) {
            return Optional.empty();
        }
        List<Lender> offers = offerSelector.electOffers(loanAmountRequested);
        Repayment repayment = repaymentCalculator.calculateRepayment(loanAmountRequested, offers);
        return Optional.of(new Quote(loanAmountRequested,
                                     repayment.getInterestRate(),
                                     repayment.getMonthlyAmount(),
                                     repayment.getTotalAmount()));
    }
}
