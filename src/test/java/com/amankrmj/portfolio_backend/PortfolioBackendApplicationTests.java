package com.amankrmj.portfolio_backend;

import com.amankrmj.portfolio_backend.services.EmailValidator;
import com.amankrmj.portfolio_backend.services.PhoneValidator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("local")
class PortfolioBackendApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(PortfolioBackendApplicationTests.class);

    @Autowired
    private PhoneValidator phoneValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Test
    void contextLoads() {
    }

//    @Test
//    void testPhoneValidationResponse() throws JsonProcessingException {
//        // Use a valid phone number for testing
//        String testPhone = "14152007986"; // Example US number
//        PhoneValidationResponse response = phoneValidator.validate(testPhone);
//
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonResponse = objectMapper.writeValueAsString(response);
//
//        // Print the serialized JSON response
//        System.out.println("Serialized PhoneValidationResponse: " + jsonResponse);
//        // Log the response
//        logger.info("PhoneValidationResponse: {}", response);
//
//        // Assertions to verify the response
//        Assertions.assertNotNull(response, "Response should not be null");
//        Assertions.assertEquals(testPhone, response.getPhone(), "Phone number should match");
//        Assertions.assertTrue(response.isValid(), "Phone number should be valid");
//
//        // Verify format
//        Assertions.assertNotNull(response.getFormat(), "Format should not be null");
//        Assertions.assertEquals("+14152007986", response.getFormat().getInternational(), "International format should match");
//        Assertions.assertEquals("(415) 200-7986", response.getFormat().getLocal(), "Local format should match");
//
//        // Verify country details
//        Assertions.assertNotNull(response.getCountry(), "Country should not be null");
//        Assertions.assertEquals("US", response.getCountry().getCode(), "Country code should match");
//        Assertions.assertEquals("United States", response.getCountry().getName(), "Country name should match");
//        Assertions.assertEquals("+1", response.getCountry().getPrefix(), "Country prefix should match");
//
//        // Verify location, type, and carrier
//        Assertions.assertEquals("California, San Francisco", response.getLocation(), "Location should match");
//        Assertions.assertEquals("mobile", response.getType(), "Type should match");
//        Assertions.assertEquals("T-mobile USA Inc.", response.getCarrier(), "Carrier should match");
//    }
//
//    @Test
//    void testEmailValidationResponse() throws JsonProcessingException {
//        // Use a valid email for testing
//        String testEmail = "amankrmj@gmail.com";
//        EmailValidationResponse response = emailValidator.validate(testEmail);
//        // Serialize the response to JSON
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonResponse = objectMapper.writeValueAsString(response);
//
//        // Print the serialized JSON response
//        System.out.println("Serialized EmailValidationResponse: " + jsonResponse);
//
//        // Log the response
//        logger.info("EmailValidationResponse: {}", response);
//
//        // Assertions to verify the response
//        Assertions.assertNotNull(response, "Response should not be null");
//        Assertions.assertEquals(testEmail, response.getEmail(), "Email should match");
//        Assertions.assertEquals("DELIVERABLE", response.getDeliverability(), "Deliverability should match");
//        Assertions.assertEquals("0.95", response.getQuality_score(), "Quality score should match");
//
//        // Verify format
//        Assertions.assertNotNull(response.getIs_valid_format(), "Valid format should not be null");
//        Assertions.assertTrue(response.getIs_valid_format().isValue(), "Valid format value should be true");
//        Assertions.assertEquals("TRUE", response.getIs_valid_format().getText(), "Valid format text should match");
//
//        // Verify free email
//        Assertions.assertNotNull(response.getIs_free_email(), "Free email should not be null");
//        Assertions.assertTrue(response.getIs_free_email().isValue(), "Free email value should be true");
//        Assertions.assertEquals("TRUE", response.getIs_free_email().getText(), "Free email text should match");
//
//        // Verify disposable email
//        Assertions.assertNotNull(response.getIs_disposable_email(), "Disposable email should not be null");
//        Assertions.assertFalse(response.getIs_disposable_email().isValue(), "Disposable email value should be false");
//        Assertions.assertEquals("FALSE", response.getIs_disposable_email().getText(), "Disposable email text should match");
//
//        // Verify role email
//        Assertions.assertNotNull(response.getIs_role_email(), "Role email should not be null");
//        Assertions.assertFalse(response.getIs_role_email().isValue(), "Role email value should be false");
//        Assertions.assertEquals("FALSE", response.getIs_role_email().getText(), "Role email text should match");
//
//        // Verify catchall email
//        Assertions.assertNotNull(response.getIs_catchall_email(), "Catchall email should not be null");
//        Assertions.assertFalse(response.getIs_catchall_email().isValue(), "Catchall email value should be false");
//        Assertions.assertEquals("FALSE", response.getIs_catchall_email().getText(), "Catchall email text should match");
//
//        // Verify MX record
//        Assertions.assertNotNull(response.getIs_mx_found(), "MX record should not be null");
//        Assertions.assertTrue(response.getIs_mx_found().isValue(), "MX record value should be true");
//        Assertions.assertEquals("TRUE", response.getIs_mx_found().getText(), "MX record text should match");
//
//        // Verify SMTP validation
//        Assertions.assertNotNull(response.getIs_smtp_valid(), "SMTP validation should not be null");
//        Assertions.assertTrue(response.getIs_smtp_valid().isValue(), "SMTP validation value should be true");
//        Assertions.assertEquals("TRUE", response.getIs_smtp_valid().getText(), "SMTP validation text should match");
//    }

}
