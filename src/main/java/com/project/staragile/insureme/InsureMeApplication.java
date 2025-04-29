package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsureMeApplication implements CommandLineRunner {

    @Autowired
    private PolicyRepository policyRepository;

    public static void main(String[] args) {
        SpringApplication.run(InsureMeApplication.class, args);
    }

    // Preload some sample data into the H2 database
    @Override
