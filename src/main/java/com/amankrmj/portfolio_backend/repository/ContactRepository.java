package com.amankrmj.portfolio_backend.repository;

import com.amankrmj.portfolio_backend.model.contact.details.ContactModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<ContactModel, Long> {
    // Additional query methods can be defined here if needed
}
