package com.tourdesign.platform.exception;

public class TourDesignException extends RuntimeException {
    public TourDesignException(String message) {
        super(message);
    }

    public TourDesignException(String message, Throwable cause) {
        super(message, cause);
    }
}