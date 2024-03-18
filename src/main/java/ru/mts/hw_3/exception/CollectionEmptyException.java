package ru.mts.hw_3.exception;

import java.util.concurrent.ExecutionException;

public class CollectionEmptyException extends ExecutionException {
    public CollectionEmptyException(String message) {
        super(message);
    }
}