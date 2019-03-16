package com.ricardo.ratecalculator;

import static org.assertj.core.api.Assertions.assertThat;

import com.ricardo.ratecalculator.model.Quote;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Locale;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ConsoleOutputPrinterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream outStream = System.out;

    @BeforeEach
    public void before() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void shouldPrintFormattedQuoteToConsole() {
        OutputPrinter underTest = new ConsoleOutputPrinter();

        underTest.printQuote(new Quote(1000, 0.07, 30.78, 1108.10), Locale.UK);

        String[] actual = outContent.toString().split(System.getProperty("line.separator"));
        assertThat(actual).hasSize(4);
        assertThat(actual[0]).isEqualTo("Requested amount: £1,000.00");
        assertThat(actual[1]).isEqualTo("Rate: 7.0%");
        assertThat(actual[2]).isEqualTo("Monthly repayment: £30.78");
        assertThat(actual[3]).isEqualTo("Total repayment: £1,108.10");
    }

    @AfterEach
    public void after() {
        System.setOut(outStream);
    }

}