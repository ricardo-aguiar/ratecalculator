package com.ricardo.ratecalculator.exception;

public class MalformedCsvFileException extends RuntimeException {

    public MalformedCsvFileException(String message) {
        super(message);
    }

    public MalformedCsvFileException(String message, Exception e) {
        super(message, e);
    }
}
