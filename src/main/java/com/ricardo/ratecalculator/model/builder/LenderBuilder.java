package com.ricardo.ratecalculator.model.builder;

import com.ricardo.ratecalculator.model.Lender;
import java.math.BigDecimal;


public final class LenderBuilder {

    private String name;
    private double interestRate;
    private BigDecimal availableFunds;
    private BigDecimal loanedFunds;

    private LenderBuilder() {
    }

    private LenderBuilder(String name, double interestRate, BigDecimal availableFunds, BigDecimal loanedFunds) {
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

    public LenderBuilder withAvailableFunds(BigDecimal availableFunds) {
        this.availableFunds = availableFunds;
        return this;
    }

    public LenderBuilder withLoanedFunds(BigDecimal loanedFunds) {
        this.loanedFunds = loanedFunds;
        return this;
    }

    public Lender build() {
        return new Lender(name, interestRate, availableFunds, loanedFunds);
    }
}

