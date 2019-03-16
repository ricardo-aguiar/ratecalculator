package com.ricardo.ratecalculator;

import com.ricardo.ratecalculator.calculator.InterestCalculator;
import com.ricardo.ratecalculator.calculator.RepaymentCalculator;
import com.ricardo.ratecalculator.exception.MalformedCsvFileException;
import com.ricardo.ratecalculator.exception.UnableToCalculateRepaymentException;
import com.ricardo.ratecalculator.model.Quote;
import com.ricardo.ratecalculator.repository.InMemLenderDataRepository;
import com.ricardo.ratecalculator.service.OfferSelector;
import com.ricardo.ratecalculator.service.QuoteService;
import com.ricardo.ratecalculator.validation.ParameterValidator;
import com.ricardo.ratecalculator.validation.annotation.FileExists;
import com.ricardo.ratecalculator.validation.annotation.ValidLoanAmountInterval;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Spec;

@Command(name = "java -jar quote.jar", mixinStandardHelpOptions = true, version = "Rate Calculator version 0.0.1-SNAPSHOT")
public class RateCalculatorApp implements Runnable {

    @NotNull
    @FileExists
    @Parameters(index = "0", paramLabel = "[FILE]", description = "The Input CSV file containing a list of all the offers made by lenders")
    private File inputFile;

    @NotNull
    @Range(min = 1000, max = 15000, message = "Loan amount must be between £{min} and £{max}")
    @ValidLoanAmountInterval
    @Parameters(index = "1",
                paramLabel = "[LOAN_AMOUNT]",
                description = "The loan amount requested by a Borrower, minimum £1,000 and maximum £15,000 inclusive and in increments of 100's")
    private int loanAmount;

    @Option(names = {"-t", "--term"},
            required = false,
            defaultValue = "36",
            description = "An optional to change the repayment terms in months, default value = ${DEFAULT-VALUE}")
    @Min(value = 1)
    private int termInMonths;

    @Spec
    private CommandSpec spec;

    private OutputPrinter OutputPrinter;

    public RateCalculatorApp() {

    }

    public RateCalculatorApp(File inputFile, int loanAmount, int termInMonths, CommandSpec spec) {
        this.inputFile = inputFile;
        this.loanAmount = loanAmount;
        this.termInMonths = termInMonths;
        this.spec = spec;
    }

    public static void main(String[] args) {
        CommandLine.run(new RateCalculatorApp(), args);
    }

    @Override
    public void run() {
        OutputPrinter = new ConsoleOutputPrinter();
        ParameterValidator parameterValidator = new ParameterValidator(this);

        List<String> violationMessages = parameterValidator.validate();
        if (!violationMessages.isEmpty()) {
            OutputPrinter.printError(String.join("\n", violationMessages));
            System.exit(-1);
        }

        InMemLenderDataRepository inMemLenderDataRepository = initializeDataRepo();
        RepaymentCalculator repaymentCalculator = new RepaymentCalculator(new InterestCalculator(), termInMonths);
        OfferSelector lendersSelector = new OfferSelector(inMemLenderDataRepository);
        QuoteService quoteService = new QuoteService(lendersSelector, repaymentCalculator);
        try {
            Optional<Quote> quote = quoteService.getQuote(loanAmount);
            quote.ifPresentOrElse(q -> OutputPrinter.printQuote(q, Locale.getDefault()),
                                  OutputPrinter::printInsufficientFundsMessage);
            System.exit(0);
        } catch (UnableToCalculateRepaymentException e) {
            OutputPrinter.printError(e.getMessage());
            System.exit(-1);
        }
    }

    private InMemLenderDataRepository initializeDataRepo() {
        InMemLenderDataRepository inMemLenderDataRepository = new InMemLenderDataRepository();
        try {
            inMemLenderDataRepository.init(inputFile);
        } catch (MalformedCsvFileException | IOException e) {
            OutputPrinter.printStackTrace(e);
            System.exit(-1);
        }
        return inMemLenderDataRepository;
    }
}
