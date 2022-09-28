package com.challenge.transportCH.exception;

public class ConnectionInvalidRequestException extends RuntimeException {

    private final Class<?> type;
    private final String message;

    public ConnectionInvalidRequestException(Class<?> type, String message) {
        this.type = type;
        this.message = message;
    }

    public ConnectionInvalidRequestException(Class<?> type) {
        this(type, "Requested should contain start location and end destination.");
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
