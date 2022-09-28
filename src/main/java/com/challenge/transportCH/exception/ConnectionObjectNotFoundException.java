package com.challenge.transportCH.exception;

public class ConnectionObjectNotFoundException extends RuntimeException {

    private final String from;
    private final String to;
    private final Class<?> type;
    private final String message;

    public ConnectionObjectNotFoundException(String from, String to, Class<?> type, String message) {
        this.from = from;
        this.to = to;
        this.type = type;
        this.message = message;
    }

    public ConnectionObjectNotFoundException(String from, String to, Class<?> type) {
        this(from, to, type, "Requested location not found: ");
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
