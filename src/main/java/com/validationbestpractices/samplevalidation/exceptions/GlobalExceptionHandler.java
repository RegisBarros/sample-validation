package com.validationbestpractices.samplevalidation.exceptions;

import com.validationbestpractices.samplevalidation.common.http.BadRequestResponseEntity;
import com.validationbestpractices.samplevalidation.common.http.InternalServerErrorResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(Exception exception, WebRequest request) {
        var validationError = ((ValidationException) exception);

        return BadRequestResponseEntity.build(validationError.getErrors());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> genericException(Exception exception, WebRequest request) {

        log.error("exception : " + exception.getLocalizedMessage() + " for " + request.getContextPath());

        return InternalServerErrorResponseEntity.build(exception.getMessage());
    }
}