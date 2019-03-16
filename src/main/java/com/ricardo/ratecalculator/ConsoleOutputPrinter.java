package com.ricardo.ratecalculator;

import com.ricardo.ratecalculator.model.Quote;
import java.text.NumberFormat;
import java.util.Locale;

public class ConsoleOutputPrinter implements OutputPrinter {

    @Override
    public void printQuote(Quote quote, Locale locale) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
        NumberFormat percentFormat = NumberFormat.getPercentInstance(locale);
        percentFormat.setMinimumFractionDigits(1);
        System.out.println(String.format( "Requested amount: %s", currencyFormat.format(quote.getRequestedAmount())));
        System.out.println(String.format( "Rate: %s", percentFormat.format(quote.getInterestRate())));
        System.out.println(String.format( "Monthly repayment: %s", currencyFormat.format(quote.getMonthlyRepayment())));
        System.out.println(String.format( "Total repayment: %s", currencyFormat.format(quote.getTotalRepayment())));
    }

    @Override
    public void printInsufficientFundsMessage() {
        System.out.println("Unable to provide a quote due to insufficient offers in the market");
    }

    @Override
    public void printStackTrace(Exception e) {
        e.printStackTrace();
    }

    @Override
    public void printError(String errorMessages) {
        System.err.println(errorMessages);
    }
}
