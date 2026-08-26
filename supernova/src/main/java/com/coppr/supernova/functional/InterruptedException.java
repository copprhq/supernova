package com.coppr.supernova.functional;

public class InterruptedException extends Exception {

    public InterruptedException(String message) {
        super(message);
    }

    public InterruptedException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public InterruptedException(Throwable throwable) {
        super(throwable);
    }
}
