package com.validationbestpractices.samplevalidation.common;

import lombok.Value;

@Value
public class ValidationResult {
    private final boolean isValid;
    private final String errorMessage;

    public static ValidationResult valid() {
        return new ValidationResult(true, null);
    }

    public static ValidationResult invalid(String errorMessage){
        return new ValidationResult(false, errorMessage);
    }

    public boolean notValid(){
        return !isValid;
    }
}