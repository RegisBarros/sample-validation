package com.validationbestpractices.samplevalidation.common.http;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

@Getter
public class BadRequestResponseEntity extends HttpResponseEntity{

    private static final String DEFAULT_MESSAGE = "One or more validation failures have occurred.";
    private final Map<String, List<String>> details;

    private BadRequestResponseEntity(@Nullable Map<String, List<String>> details) {
        super(HttpStatus.BAD_REQUEST, DEFAULT_MESSAGE);
        this.details = details;
    }

    public static ResponseEntity<BadRequestResponseEntity> build(@Nullable Map<String, List<String>> details){
        var badRequest = new BadRequestResponseEntity(details);

        return new ResponseEntity<>(badRequest, badRequest.getStatus());
    }
}
