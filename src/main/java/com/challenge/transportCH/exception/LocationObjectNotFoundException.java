package com.challenge.transportCH.exception;

public class LocationObjectNotFoundException extends RuntimeException {

    private final String name;
    private final Class<?> type;
    private final String message;

    public LocationObjectNotFoundException(String name, Class<?> type, String message) {
        this.name = name;
        this.type = type;
        this.message = message;
    }

    public LocationObjectNotFoundException(String name, Class<?> type) {
        this(name, type, "Requested location not found: ");
    }

    public String getName() {
        return name;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
