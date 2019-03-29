package com.ricardo.ratecalculator.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Lender {

    private final String name;
    private final double interestRate;
    private final BigDecimal availableFunds;
    private final BigDecimal loanedFunds;

    public Lender(String name, double interestRate, BigDecimal availableFunds, BigDecimal loanedFunds) {
        this.name = name;
        this.interestRate = interestRate;
        this.availableFunds = availableFunds;
        this.loanedFunds = loanedFunds;
    }

    public Lender(String name, double interestRate, BigDecimal availableFunds) {
        this(name, interestRate, availableFunds, new BigDecimal("0"));
    }

    public String getName() {
        return name;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public BigDecimal getAvailableFunds() {
        return availableFunds;
    }

    public BigDecimal getLoanedFunds() {
        return loanedFunds;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Lender lender = (Lender) other;
        return Double.compare(lender.interestRate, interestRate) == 0 &&
               name.equals(lender.name) &&
               availableFunds.compareTo(lender.availableFunds) == 0 &&
               loanedFunds.compareTo(lender.loanedFunds) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, interestRate, availableFunds, loanedFunds);
    }

    @Override
    public String toString() {
        return "Lender{"
               + "name='" + name + '\''
               + ", interestRate=" + interestRate
               + ", availableFunds=" + availableFunds
               + ", loanedFunds=" + loanedFunds
               + '}';
    }
}
