package com.ricardo.ratecalculator.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

import com.ricardo.ratecalculator.exception.MalformedCsvFileException;
import com.ricardo.ratecalculator.model.Lender;
import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InMemLenderDataRepositoryTest {

    private InMemLenderDataRepository underTest;

    @BeforeEach
    void setUp() {
        underTest = new InMemLenderDataRepository();
    }

    @Test
    @DisplayName("It should return a list of lenders ordered by interest rate in ascending order")
    public void shouldReturnAListOfLendersOrderedByInterestRate() throws IOException {
        underTest.init(new File(getClass().getResource("/market_data.csv").getFile()));

        List<Lender> actual = underTest.findAllOrderedByInterestRateAsc();

        assertThat(actual).hasSize(7);
        assertThat(actual).isSortedAccordingTo(Comparator.comparing(Lender::getInterestRate));
    }

    @Test
    @DisplayName("It should throw an exception if it fails to parse file due to malformed data")
    public void shouldThrowAnExceptionIfFailsToInitializeRepo() {

        Throwable throwable = catchThrowable(() -> underTest.init(new File(getClass().getResource("/malformed_market_data.csv").getFile())));

        assertThat(throwable).isInstanceOf(MalformedCsvFileException.class);
        assertThat(throwable).hasMessage("There was problem parsing the market CSV file malformed_market_data.csv");
        assertThat(throwable).hasCauseExactlyInstanceOf(NumberFormatException.class);

    }

    @Test
    @DisplayName("It should throw an IO exception if it fails to open a file")
    public void shouldThrowAnIOExceptionIfFailsToOpenAFile() {

        Throwable throwable = catchThrowable(() -> underTest.init(new File("noa file")));

        assertThat(throwable).isInstanceOf(NoSuchFileException.class);
    }

    @Test
    @DisplayName("It should throw an exception if it fails to parse file due to missing columns data")
    public void shouldThrowAnExceptionIfMissingCollumnsInCSVFile() {

        Throwable throwable = catchThrowable(() -> underTest.init(new File(getClass().getResource("/malformed_market_data.csv").getFile())));

        assertThat(throwable).isInstanceOf(MalformedCsvFileException.class);
        assertThat(throwable).hasMessage("There was problem parsing the market CSV file malformed_market_data.csv");
        assertThat(throwable).hasCauseExactlyInstanceOf(NumberFormatException.class);

    }

}