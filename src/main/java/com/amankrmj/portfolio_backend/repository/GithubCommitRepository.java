package com.amankrmj.portfolio_backend.repository;

import com.amankrmj.portfolio_backend.model.github.GithubCommitModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GithubCommitRepository extends CrudRepository<GithubCommitModel, Long> {
    // Additional query methods can be defined here if needed
}
