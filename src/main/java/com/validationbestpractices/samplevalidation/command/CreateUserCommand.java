package com.validationbestpractices.samplevalidation.command;

import lombok.Data;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@Value
public class CreateUserCommand {
    @Size(min = 2, max = 30)
    @NotBlank
    String name;

    @NotNull
    LocalDate birthDate;
}