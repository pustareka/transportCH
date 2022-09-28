package com.challenge.transportCH.exception;

public class LocationInvalidRequestException extends RuntimeException {

    private final Class<?> type;
    private final String message;

    public LocationInvalidRequestException(Class<?> type, String message) {
        this.type = type;
        this.message = message;
    }

    public LocationInvalidRequestException( Class<?> type) {
        this(type, "Requested should contain station name or coordinates.");
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
