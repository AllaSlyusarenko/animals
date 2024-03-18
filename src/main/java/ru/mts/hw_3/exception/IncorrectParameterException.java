package ru.mts.hw_3.exception;

public class IncorrectParameterException extends IllegalArgumentException {
    public IncorrectParameterException(String message) {
        super(message);
    }
}