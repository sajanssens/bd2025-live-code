package com.infosupport.jeedemo.api.exceptions;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

@Provider
public class QueryParamTooLongExceptionMapper implements ExceptionMapper<QueryParamTooLongException> {

    @Override
    public Response toResponse(QueryParamTooLongException exception) {
        return Response.status(BAD_REQUEST)
                .entity("Query not ok " + exception.getMessage() + ".")
                .build();
    }
}
