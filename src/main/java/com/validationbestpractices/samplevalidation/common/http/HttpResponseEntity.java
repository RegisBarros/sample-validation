package com.validationbestpractices.samplevalidation.common.http;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class HttpResponseEntity {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final HttpStatus status;
    private final String message;
    private final String traceId;

    public HttpResponseEntity(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        this.traceId = UUID.randomUUID().toString();
    }
}
