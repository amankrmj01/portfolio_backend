package com.amankrmj.portfolio_backend.controller;

import com.amankrmj.portfolio_backend.model.email.EmailValidationResponse;
import com.amankrmj.portfolio_backend.services.EmailValidator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailValidatorController {
    private final EmailValidator emailValidator;

    public EmailValidatorController(EmailValidator emailValidator) {
        this.emailValidator = emailValidator;
    }

    @GetMapping("/validate")
    public EmailValidationResponse validateEmail(@RequestParam String email) {
        return emailValidator.validate(email);
    }
}
