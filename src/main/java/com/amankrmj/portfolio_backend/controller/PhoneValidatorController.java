//package com.amankrmj.portfolio_backend.controller;
//
//import com.amankrmj.portfolio_backend.model.phone.PhoneValidationResponse;
//import com.amankrmj.portfolio_backend.services.PhoneValidator;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/phone")
//public class PhoneValidatorController {
//
//    private final PhoneValidator phoneValidator;
//
//    public PhoneValidatorController(PhoneValidator phoneValidator) {
//        this.phoneValidator = phoneValidator;
//    }
//
//    @GetMapping("/validate")
//    public PhoneValidationResponse validatePhone(@RequestParam String phone) {
//        return phoneValidator.validate(phone);
//    }
//}
