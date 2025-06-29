package com.amankrmj.portfolio_backend.controller;

import com.amankrmj.portfolio_backend.model.contact.details.ContactModel;
import com.amankrmj.portfolio_backend.model.github.Committer;
import com.amankrmj.portfolio_backend.model.github.GithubCommitModel;
import com.amankrmj.portfolio_backend.services.EmailValidator;
import com.amankrmj.portfolio_backend.services.GithubCommitService;
import com.amankrmj.portfolio_backend.services.PhoneValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/add")
public class GithubCommitController {

    private static final Logger logger = LoggerFactory.getLogger(GithubCommitController.class);

    private final GithubCommitService githubCommitService;
    private final EmailValidator emailValidatorService;
    private final PhoneValidator phoneValidatorService;
    private final ObjectMapper objectMapper;

    public GithubCommitController(
            GithubCommitService githubCommitService,
            EmailValidator emailValidatorService,
            PhoneValidator phoneValidatorService,
            ObjectMapper objectMapper
    ) {
        this.githubCommitService = githubCommitService;
        this.emailValidatorService = emailValidatorService;
        this.phoneValidatorService = phoneValidatorService;
        this.objectMapper = objectMapper;
    }

    @PostMapping("/contact")
    public ResponseEntity<Map<String, String>> commitContactForm(
            @Valid @RequestBody ContactModel contactForm,
            @RequestHeader(value = "User-Agent", required = false) String userAgent,
            jakarta.servlet.http.HttpServletRequest request) {

        String remoteAddress = request.getHeader("X-Forwarded-For");
        if (remoteAddress == null || remoteAddress.isEmpty() || "unknown".equalsIgnoreCase(remoteAddress)) {
            remoteAddress = request.getRemoteAddr();
        } else {
            // X-Forwarded-For can contain multiple IPs, take the first one
            remoteAddress = remoteAddress.split(",")[0].trim();
        }
        logger.info("Request received from User-Agent: {}, Address: {}", userAgent, remoteAddress);


        // Validate phone
        var phoneValidationResponse = phoneValidatorService.validate(contactForm.getCountryCode() + contactForm.getPhoneNumber());
        if (!phoneValidationResponse.isValid()) {
            Map<String, String> errorMap = new java.util.HashMap<>();
            errorMap.put("status", "400");
            errorMap.put("message", "Invalid phone number");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }

        contactForm.setCountryCode(phoneValidationResponse.getCountry().getPrefix());
        String trimmedLocal = phoneValidationResponse.getFormat().getLocal()
                .replaceFirst("^0", "") // Remove leading 0
                .replaceAll("\\s", ""); // Remove spaces

        contactForm.setPhoneNumber(trimmedLocal);
        // Validate email
        var emailValidationResponse = emailValidatorService.validate(contactForm.getEmail());
        if (!emailValidationResponse.getIs_valid_format().isValue()) {
            Map<String, String> errorMap = new java.util.HashMap<>();
            errorMap.put("status", "400");
            errorMap.put("message", "Invalid email address");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        if (emailValidationResponse.getIs_disposable_email().isValue()) {
            Map<String, String> errorMap = new java.util.HashMap<>();
            errorMap.put("status", "400");
            errorMap.put("message", "Disposable email address not allowed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
        }
        contactForm.setEmail(emailValidationResponse.getAutocorrect());
        try {
            String contactJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(contactForm);
            String contentBase64 = Base64.getEncoder().encodeToString(contactJson.getBytes(StandardCharsets.UTF_8));

            GithubCommitModel commitModel = new GithubCommitModel(
                    contactForm.getName() + " wants to contact you",
                    contentBase64,
                    new Committer(contactForm.getName(), contactForm.getEmail())
            );
            String formatted = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String filePath = "contact" + formatted + ".json";

            return githubCommitService.createOrUpdateFile(filePath, commitModel);
        } catch (Exception e) {
            // This catch is only for serialization or local errors, not for service errors
            logger.error("Failed to process contact form", e);
            Map<String, String> errorMap = new java.util.HashMap<>();
            errorMap.put("status", "500");
            errorMap.put("message", "Failed to process contact form: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMap);
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new java.util.HashMap<>();
        errors.put("status", "400");
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .reduce((msg1, msg2) -> msg1 + ", " + msg2)
                .orElse("Invalid input");
        errors.put("message", errorMessage);
        return ResponseEntity.badRequest().body(errors);
    }
}
