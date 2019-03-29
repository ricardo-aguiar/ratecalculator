package com.ricardo.ratecalculator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Repayment {

    private final BigDecimal monthlyAmount;
    private final BigDecimal totalAmount;
    private final double interestRate;

    public Repayment(BigDecimal monthlyAmount, BigDecimal totalAmount, double interestRate) {

        this.monthlyAmount = monthlyAmount;
        this.totalAmount = totalAmount;
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyAmount() {
        return monthlyAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Repayment repayment = (Repayment) other;
        return Double.compare(repayment.interestRate, interestRate) == 0 &&
               monthlyAmount.compareTo(repayment.monthlyAmount) == 0 &&
               totalAmount.compareTo(repayment.totalAmount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monthlyAmount, totalAmount, interestRate);
    }

    @Override
    public String toString() {
        return "Repayment{"
               + "monthlyAmount=" + monthlyAmount
               + ", totalAmount=" + totalAmount
               + ", interestRate=" + interestRate
               + '}';
    }
}
