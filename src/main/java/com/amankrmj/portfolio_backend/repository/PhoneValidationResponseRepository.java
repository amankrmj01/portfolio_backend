package com.amankrmj.portfolio_backend.repository;

import com.amankrmj.portfolio_backend.model.phone.PhoneValidationResponse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneValidationResponseRepository extends CrudRepository<PhoneValidationResponse, Long> {
    // Additional query methods can be defined here if needed
}
