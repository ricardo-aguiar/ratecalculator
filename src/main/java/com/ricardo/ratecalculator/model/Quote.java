package com.ricardo.ratecalculator.model;

import java.util.Objects;

public class Quote {

    private final int requestedAmount;
    private final double interestRate;
    private final double monthlyRepayment;
    private final double totalRepayment;

    public Quote(int requestedAmount, double interestRate, double monthlyRepayment, double totalRepayment) {

        this.requestedAmount = requestedAmount;
        this.interestRate = interestRate;
        this.monthlyRepayment = monthlyRepayment;
        this.totalRepayment = totalRepayment;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public double getTotalRepayment() {
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
        return requestedAmount == quote.requestedAmount &&
               Double.compare(quote.interestRate, interestRate) == 0 &&
               Double.compare(quote.monthlyRepayment, monthlyRepayment) == 0 &&
               Double.compare(quote.totalRepayment, totalRepayment) == 0;
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
