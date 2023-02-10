package com.validationbestpractices.samplevalidation.common.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class InternalServerErrorResponseEntity extends HttpResponseEntity{
    private InternalServerErrorResponseEntity(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, message, "https://tools.ietf.org/html/rfc7231#section-6.6.1");
    }

    public static ResponseEntity<InternalServerErrorResponseEntity> build(String message){
        var responseEntity = new InternalServerErrorResponseEntity(message);

        return new ResponseEntity<>(responseEntity, responseEntity.getStatus());
    }
}
