package com.coppr.supernova.functional;

public class ResultInterruptedException extends Exception {

    public ResultInterruptedException(String message) {
        super(message);
    }

    public ResultInterruptedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public ResultInterruptedException(Throwable throwable) {
        super(throwable);
    }
}
