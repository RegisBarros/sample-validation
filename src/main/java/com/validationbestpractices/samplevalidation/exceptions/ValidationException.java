package com.validationbestpractices.samplevalidation.exceptions;

import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ValidationException extends RuntimeException {

    private final Map<String, List<String>> errors;

    public ValidationException() {
        super("One or more validation failures have occurred.");
        errors = new HashMap<>();
    }

    public ValidationException(Set<? extends ConstraintViolation<?>> violations) {
        errors = violations.stream()
                .collect(Collectors.groupingBy(violation -> violation.getPropertyPath().toString(),
                        Collectors.mapping(ConstraintViolation::getMessage, Collectors.toList())));
    }
}
