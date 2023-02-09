package com.validationbestpractices.samplevalidation.common;

import com.validationbestpractices.samplevalidation.exceptions.ValidationException;

import javax.validation.*;
import java.util.Set;

public abstract class SelfValidating<T> {

    private Validator validator;
    private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    public SelfValidating() {
        validator = factory.getValidator();
    }

    public void validate() {
        Set<ConstraintViolation<T>> violations = validator.validate((T) this);
        if(!violations.isEmpty()){
            throw new ValidationException(violations);
        }
    }
}
