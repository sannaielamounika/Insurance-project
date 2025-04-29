package com.project.staragile.insureme;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, Integer> {
    // JpaRepository already provides basic CRUD methods, no additional methods are needed
}

