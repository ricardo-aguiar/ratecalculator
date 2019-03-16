package com.ricardo.ratecalculator;

import com.ricardo.ratecalculator.model.Quote;
import java.util.Locale;

public interface OutputPrinter {

    void printQuote(Quote quote, Locale locale);

    void printInsufficientFundsMessage();

    void printStackTrace(Exception e);

    void printError(String errorMessages);
}
