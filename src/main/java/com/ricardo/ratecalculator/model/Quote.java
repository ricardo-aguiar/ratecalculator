package com.ricardo.ratecalculator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Quote {

    private final BigDecimal requestedAmount;
    private final double interestRate;
    private final BigDecimal monthlyRepayment;
    private final BigDecimal totalRepayment;

    public Quote(BigDecimal requestedAmount, double interestRate, BigDecimal monthlyRepayment, BigDecimal totalRepayment) {
        this.requestedAmount = requestedAmount;
        this.interestRate = interestRate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public BigDecimal getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public BigDecimal getTotalRepayment() {
        return totalRepayment;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Quote quote = (Quote) other;
        return Double.compare(quote.interestRate, interestRate) == 0 &&
               requestedAmount.equals(quote.requestedAmount) &&
               monthlyRepayment.equals(quote.monthlyRepayment) &&
               totalRepayment.equals(quote.totalRepayment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestedAmount, interestRate, monthlyRepayment, totalRepayment);
    }

    @Override
    public String toString() {
        return "Quote{"
               + "requestedAmount=" + requestedAmount
               + ", interestRate=" + interestRate
               + ", monthlyRepayment=" + monthlyRepayment
               + ", totalRepayment=" + totalRepayment
               + '}';
    }
}
