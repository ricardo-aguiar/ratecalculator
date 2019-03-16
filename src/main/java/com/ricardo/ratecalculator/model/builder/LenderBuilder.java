package com.ricardo.ratecalculator.model.builder;

import com.ricardo.ratecalculator.model.Lender;


public final class LenderBuilder {

    private String name;
    private double interestRate;
    private int availableFunds;
    private int loanedFunds;

    private LenderBuilder() {
    }

    private LenderBuilder(String name, double interestRate, int availableFunds, int loanedFunds) {
        this.name = name;
        this.interestRate = interestRate;
        this.availableFunds = availableFunds;
        this.loanedFunds = loanedFunds;
    }

    public static LenderBuilder aLender() {
        return new LenderBuilder();
    }

    public static LenderBuilder copy(Lender lender) {
        return new LenderBuilder(lender.getName(), lender.getInterestRate(), lender.getAvailableFunds(), lender.getLoanedFunds());
    }

    public LenderBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public LenderBuilder withInterestRate(double interestRate) {
        this.interestRate = interestRate;
        return this;
    }

    public LenderBuilder withAvailableFunds(int availableFunds) {
        this.availableFunds = availableFunds;
        return this;
    }

    public LenderBuilder withLoanedFunds(int loanedFunds) {
        this.loanedFunds = loanedFunds;
        return this;
    }

    public Lender build() {
        return new Lender(name, interestRate, availableFunds, loanedFunds);
    }
}

