package com.validationbestpractices.samplevalidation.service;

import com.validationbestpractices.samplevalidation.command.CreateUserCommand;
import com.validationbestpractices.samplevalidation.common.ValidationResult;
import com.validationbestpractices.samplevalidation.model.User;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UserService {

    public User create(CreateUserCommand command) {

        var result = new CreateUserCommand.CreateUserCommandValidate<CreateUserCommand>()
                .validate(command);

        var user = new User(1, command.getName(), command.getBirthDate());

        return user;
    }
}
