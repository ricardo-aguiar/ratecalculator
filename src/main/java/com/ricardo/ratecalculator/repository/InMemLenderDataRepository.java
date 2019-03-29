package com.ricardo.ratecalculator.repository;

import com.ricardo.ratecalculator.exception.MalformedCsvFileException;
import com.ricardo.ratecalculator.model.Lender;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InMemLenderDataRepository implements DataRepository {

    private final List<Lender> lenders = new ArrayList<>();

    public void init(File inputFile) throws MalformedCsvFileException, IOException {
        try (Stream<String> stream = Files.lines(inputFile.toPath())) {

            lenders.addAll(stream.filter(header -> !header.equals("Lender,Rate,Available"))
                                 .map(line -> line.split(","))
                                 .map(this::buildLenders)
                                 .collect(Collectors.toList()));

        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new MalformedCsvFileException(String.format( "There was problem parsing the market CSV file %s", inputFile.getName()), e);
        }
    }

    private Lender buildLenders(String[] line) throws MalformedCsvFileException {
        if (line.length != 3) {
            throw new MalformedCsvFileException("Malformed CSV file");
        }
        return new Lender(line[0], Double.parseDouble(line[1]), new BigDecimal(line[2]));
    }


    @Override
    public List<Lender> findAllOrderedByInterestRateAsc() {
        return this.lenders.stream()
                           .sorted(Comparator.comparing(Lender::getInterestRate)
                                             .thenComparing(Lender::getAvailableFunds))
                           .collect(Collectors.toUnmodifiableList());

    }

    @Override
    public List<Lender> findAll() {
        return Collections.unmodifiableList(this.lenders);
    }

}
