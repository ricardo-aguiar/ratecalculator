package com.ricardo.ratecalculator.model;

import java.util.Objects;

public class Lender {

    private final String name;
    private final double interestRate;
    private final int availableFunds;
    private final int loanedFunds;

    public Lender(String name, double interestRate, int availableFunds, int loanedFunds) {
        this.name = name;
        this.interestRate = interestRate;
        this.availableFunds = availableFunds;
        this.loanedFunds = loanedFunds;
    }

    public Lender(String name, double interestRate, int availableFunds) {
        this(name, interestRate, availableFunds, 0);
    }

    public String getName() {
        return name;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public int getAvailableFunds() {
        return availableFunds;
    }

    public int getLoanedFunds() {
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
               availableFunds == lender.availableFunds &&
               loanedFunds == lender.loanedFunds &&
               Objects.equals(name, lender.name);
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
