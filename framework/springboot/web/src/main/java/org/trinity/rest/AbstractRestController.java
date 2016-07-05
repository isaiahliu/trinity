package org.trinity.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.trinity.rest.security.ITokenAwareAuthentication;
import org.trinity.rest.security.Token;

public abstract class AbstractRestController implements IRestController {
    private HttpHeaders httpHeaders;

    public HttpHeaders getHttpHeaders(final MediaType mediaType) {
        if (httpHeaders == null) {
            httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(mediaType);
        }
        return httpHeaders;
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

    protected <T> ResponseEntity<T> createResponseEntity(final T body, final MediaType mediaType,
            final HttpStatus status) {
        return new ResponseEntity<T>(body, getHttpHeaders(mediaType), status);
    }

    protected String getToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication instanceof ITokenAwareAuthentication) {
            final Token token = ((ITokenAwareAuthentication) authentication).getToken();
            return token.getToken();
        }
        return null;
    }
}
