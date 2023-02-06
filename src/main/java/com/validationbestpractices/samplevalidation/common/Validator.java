package com.validationbestpractices.samplevalidation.common;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public abstract class Validator {
    public static class CommandValidator<T> extends ValidationStep<T> {

        @Override
        public ValidationResult verify(T command) {
            try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                final javax.validation.Validator validator = validatorFactory.getValidator();
                final Set<ConstraintViolation<T>> constraintViolations = validator.validate(command);

                if (!constraintViolations.isEmpty()) {
                    return ValidationResult.invalid(constraintViolations.iterator().next().getMessage());
                }
            }
            return checkNext(command);
        }
    }
}
