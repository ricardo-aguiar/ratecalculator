package com.ricardo.ratecalculator.calculator;

import com.ricardo.ratecalculator.model.Lender;
import java.util.List;

public class InterestCalculator {

    double calculateMonthlyCompoundInterest(double interestRate, int term) {
        double rate = Math.pow((1.0 + interestRate), (1.0 / 12)) - 1.0;
        return rate / (1 - (Math.pow(1 + rate, -term)));
    }

    double calculateAverageInterest(List<Lender> lenders, double loanAmountRequested) {
        double weightFactors = lenders.stream()
                                       .mapToDouble(lender -> lender.getLoanedFunds() * lender.getInterestRate())
                                       .reduce(0.0, Double::sum);
        return weightFactors / loanAmountRequested;
    }

}
