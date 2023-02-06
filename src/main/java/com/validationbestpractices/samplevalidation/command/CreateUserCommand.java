package com.validationbestpractices.samplevalidation.command;

import com.validationbestpractices.samplevalidation.common.ValidationResult;
import com.validationbestpractices.samplevalidation.common.ValidationStep;
import lombok.Data;
import lombok.Value;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@Value
public class CreateUserCommand {
    @Min(2)
    @Max(30)
    @NotBlank
    String name;

    @NotNull
    LocalDate birthDate;


    public static class CreateUserCommandValidate<T> extends ValidationStep<T> {

        @Override
        public ValidationResult validate(T command) {
            try(ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
                final Validator validator = validatorFactory.getValidator();
                final Set<ConstraintViolation<T>> constraintViolations = validator.validate(command);

                if(!constraintViolations.isEmpty()) {
                    return ValidationResult.inValid(constraintViolations.iterator().next().getMessage());
                }
            }
            return  checkNext(command);
        }
    }
}