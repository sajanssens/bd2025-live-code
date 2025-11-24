package com.infosupport.jeedemo.api.exceptions;

public class QueryParamTooLongException extends RuntimeException {
    public QueryParamTooLongException(String message) {
        super(message);
    }
}
