package com.validationbestpractices.samplevalidation.controller;

import com.validationbestpractices.samplevalidation.command.CreateUserCommand;
import com.validationbestpractices.samplevalidation.exceptions.CustomException;
import com.validationbestpractices.samplevalidation.model.User;
import com.validationbestpractices.samplevalidation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1/users")
@AllArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody CreateUserCommand command) {

        command.validate();

        var user = userService.create(command);
        return ResponseEntity.ok(user);
    }
}
