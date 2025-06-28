package com.amankrmj.portfolio_backend.repository;

import com.amankrmj.portfolio_backend.model.email.EmailValidationResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailValidationResponseRepository extends CrudRepository<EmailValidationResponse, Long> {
    // Additional query methods can be defined here if needed
}
