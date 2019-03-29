package com.ricardo.ratecalculator.calculator;

import com.ricardo.ratecalculator.model.Lender;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class InterestCalculator {

    double calculateMonthlyCompoundInterest(double interestRate, int term) {
        double rate = Math.pow((1.0 + interestRate), (1.0 / 12)) - 1.0;
        return rate / (1 - (Math.pow(1 + rate, -term)));
    }

    double calculateAverageInterest(List<Lender> lenders, BigDecimal loanAmountRequested) {
        BigDecimal weightFactors = lenders.stream()
                                          .map(lender -> lender.getLoanedFunds().multiply(new BigDecimal(lender.getInterestRate())))
                                          .reduce(BigDecimal.ZERO, BigDecimal::add);
        return weightFactors.divide(loanAmountRequested, RoundingMode.CEILING).doubleValue();
    }

}
