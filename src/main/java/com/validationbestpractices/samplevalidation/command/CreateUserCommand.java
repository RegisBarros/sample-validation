package com.validationbestpractices.samplevalidation.command;

import com.validationbestpractices.samplevalidation.common.SelfValidating;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Value
@EqualsAndHashCode(callSuper = false)
public class CreateUserCommand extends SelfValidating<CreateUserCommand> {
    @Size(min = 2, max = 30)
    @NotBlank
    String name;

    @NotNull
    LocalDate birthDate;

    public CreateUserCommand(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        this.validateSelf();
    }
}