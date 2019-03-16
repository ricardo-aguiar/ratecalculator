package com.ricardo.ratecalculator.calculator;

import com.ricardo.ratecalculator.exception.UnableToCalculateRepaymentException;
import com.ricardo.ratecalculator.model.Lender;
import com.ricardo.ratecalculator.model.Repayment;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class RepaymentCalculator {

    private final InterestCalculator interestCalculator;
    private final int term;

    public RepaymentCalculator(InterestCalculator interestCalculator, int term) {
        this.interestCalculator = interestCalculator;
        this.term = term;
    }

    public Repayment calculateRepayment(int loanAmountRequested, List<Lender> lenders) throws UnableToCalculateRepaymentException {
        double loanAmount = 1.0 * loanAmountRequested;
        double averageInterest = round(interestCalculator.calculateAverageInterest(lenders, loanAmount), 2);
        if (averageInterest <= 0.0) {
            // Not supporting interest free repayment calculation as the output of the tool
            // does not support breakdown of unequal monthly installments
            throw new UnableToCalculateRepaymentException("Repayment calculation for interest free loan is not supported yet");
        }
        double monthlyCompoundInterest = interestCalculator.calculateMonthlyCompoundInterest(averageInterest, term);
        double monthlyRepay = round(monthlyCompoundInterest * loanAmountRequested, 2);
        double totalRepay = round(monthlyRepay * term, 2);
        return new Repayment(monthlyRepay, totalRepay, averageInterest);
    }

    private double round(double value, int precision) {
        BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
        return bigDecimal.setScale(precision, RoundingMode.HALF_UP).doubleValue();
    }
}
