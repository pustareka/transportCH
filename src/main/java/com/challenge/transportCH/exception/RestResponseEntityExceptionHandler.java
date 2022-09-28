package com.challenge.transportCH.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.Collections;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LocationObjectNotFoundException.class)
    protected ResponseEntity<Object> handleLocationNotFoundException(LocationObjectNotFoundException exception, WebRequest webRequest) {
        ResponseMessage bodyOfResponse = new ResponseMessage(exception.getMessage() + exception.getName());
        return createResponseEntity(HttpStatus.NOT_FOUND, false, bodyOfResponse);
    }

    @ExceptionHandler(ConnectionObjectNotFoundException.class)
    protected ResponseEntity<Object> handleConnectionNotFoundException(ConnectionObjectNotFoundException exception, WebRequest webRequest) {
        ResponseMessage bodyOfResponse = new ResponseMessage(exception.getMessage() + exception.getFrom() + " or " + exception.getTo());
        return createResponseEntity(HttpStatus.NOT_FOUND, false, bodyOfResponse);
    }

    @ExceptionHandler({LocationInvalidRequestException.class, ConnectionInvalidRequestException.class})
    protected ResponseEntity<Object> handleInvalidLocationRequestException(Exception exception, WebRequest webRequest) {
        ResponseMessage bodyOfResponse = new ResponseMessage(exception.getMessage());
        return createResponseEntity(HttpStatus.BAD_REQUEST, false, bodyOfResponse);
    }

    @ExceptionHandler(GeneralServerError.class)
    protected ResponseEntity<Object> handleLocationNotFoundException(GeneralServerError exception, WebRequest webRequest) {
        ResponseMessage bodyOfResponse = new ResponseMessage(exception.getMessage() + exception.getMessage());
        return createResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, false, bodyOfResponse);
    }

    private ResponseEntity<Object> createResponseEntity(final HttpStatus httpStatus, final boolean status, final ResponseMessage responseMessage) {
        final ResponseStatus responseStatus = new ResponseStatus(status, Collections.singletonList(responseMessage), OffsetDateTime.now());
        return new ResponseEntity<>(responseStatus, httpStatus);
    }
}
