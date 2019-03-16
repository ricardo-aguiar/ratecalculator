package com.ricardo.ratecalculator.model;

import java.util.Objects;

public class Repayment {

    private final double monthlyAmount;
    private final double totalAmount;
    private final double interestRate;

    public Repayment(double monthlyAmount, double totalAmount, double interestRate) {

        this.monthlyAmount = monthlyAmount;
        this.totalAmount = totalAmount;
        this.interestRate = interestRate;
    }

    public double getMonthlyAmount() {
        return monthlyAmount;
    }

    public double getTotalAmount() {
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
        return Double.compare(repayment.monthlyAmount, monthlyAmount) == 0 &&
               Double.compare(repayment.totalAmount, totalAmount) == 0 &&
               Double.compare(repayment.interestRate, interestRate) == 0;
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
