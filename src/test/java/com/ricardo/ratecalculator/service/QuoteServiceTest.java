package com.ricardo.ratecalculator.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.ricardo.ratecalculator.calculator.InterestCalculator;
import com.ricardo.ratecalculator.calculator.RepaymentCalculator;
import com.ricardo.ratecalculator.exception.UnableToCalculateRepaymentException;
import com.ricardo.ratecalculator.model.Quote;
import com.ricardo.ratecalculator.repository.InMemLenderDataRepository;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class QuoteServiceTest {

    @Test
    public void shouldReturnAQuoteForAGivenAmount() throws UnableToCalculateRepaymentException, IOException {
        InMemLenderDataRepository inMemLenderDataRepository = new InMemLenderDataRepository();
        inMemLenderDataRepository.init(new File(getClass().getResource("/market_data.csv").getFile()));

        QuoteService quoteService = new QuoteService(new OfferSelector(inMemLenderDataRepository), new RepaymentCalculator(new InterestCalculator(), 36));

        Optional<Quote> actual = quoteService.getQuote(new BigDecimal("1000"));

        assertThat(actual).hasValue(new Quote(new BigDecimal("1000"), 0.07, new BigDecimal("30.78"), new BigDecimal("1108.08")));

    }

    @Test
    public void shouldReturnAnEmptyOptionalWhenThereIsInsufficientFundsToFulfillRequest() throws UnableToCalculateRepaymentException, IOException {
        InMemLenderDataRepository inMemLenderDataRepository = new InMemLenderDataRepository();
        inMemLenderDataRepository.init(new File(getClass().getResource("/market_data.csv").getFile()));

        QuoteService quoteService = new QuoteService(new OfferSelector(inMemLenderDataRepository), new RepaymentCalculator(new InterestCalculator(), 36));

        Optional<Quote> actual = quoteService.getQuote(new BigDecimal("3000"));

        assertThat(actual).isEmpty();
    }

}