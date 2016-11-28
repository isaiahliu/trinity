package org.trinity.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.trinity.common.dto.response.DefaultResponse;
import org.trinity.rest.IRestController;

public abstract class AbstractRestController implements IRestController {
    private HttpHeaders httpHeaders;

    public HttpHeaders getHttpHeaders(final MediaType mediaType) {
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(mediaType);
        }
        return httpHeaders;
    }

    protected ResponseEntity<DefaultResponse> createResponseEntity() {
        return createResponseEntity(new DefaultResponse());
    }

    protected <T> ResponseEntity<T> createResponseEntity(final T body) {
        return createResponseEntity(body, HttpStatus.OK);
    }

    protected <T> ResponseEntity<T> createResponseEntity(final T body, final HttpStatus status) {
        return createResponseEntity(body, MediaType.APPLICATION_JSON, status);
    }

    protected <T> ResponseEntity<T> createResponseEntity(final T body, final MediaType mediaType) {
        return createResponseEntity(body, mediaType, HttpStatus.OK);
    }

    protected <T> ResponseEntity<T> createResponseEntity(final T body, final MediaType mediaType, final HttpStatus status) {
        return new ResponseEntity<>(body, getHttpHeaders(mediaType), status);
    }
}
