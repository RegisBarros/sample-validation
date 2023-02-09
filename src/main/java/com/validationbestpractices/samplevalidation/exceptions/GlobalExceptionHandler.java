package com.validationbestpractices.samplevalidation.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(Exception exception, WebRequest request) {
        var details = ((ValidationException)exception).errors;
        var apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST, "Validation Exception", details);

        return ResponseEntityBuilder.build(apiError);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> genericException(Exception exception, WebRequest request) {

        log.error("exception : " +
                exception.getLocalizedMessage() +
                " for " +
                request.getContextPath());

        var apiError = new ApiError(LocalDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR, "Constraint Violation", null);

        return ResponseEntityBuilder.build(apiError);

//        return new ResponseEntity<>(
//                ApiError.builder()
//                        .errorMessage(exception.getLocalizedMessage())
//                        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
//                        .request(request.getRequestURI())
//                        .requestType(request.getMethod())
//                        .customMessage("Could not process request")
//                        .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

class ResponseEntityBuilder {
    public static ResponseEntity<Object> build(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}

@Getter
@Setter
@AllArgsConstructor
class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private Map<String, List<String>> errors;
}