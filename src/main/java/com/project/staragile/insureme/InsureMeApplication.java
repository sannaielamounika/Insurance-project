package com.project.staragile.insureme;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.project.staragile.insureme")  // Ensure all components are scanned
public class InsureMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsureMeApplication.class, args);
    }

    // Optional: Preload some data into the H2 database
    @Bean
    public CommandLineRunner loadData(PolicyRepository policyRepository) {
        return args -> {
            // Preload data if necessary
            if (policyRepository.count() == 0) {
                policyRepository.save(new Policy(0, "John Doe", "Life Insurance", 15000.0, "2024-01-01", "2034-01-01"));
                policyRepository.save(new Policy(0, "Jane Smith", "Health Insurance", 8000.0, "2024-06-01", "2029-06-01"));
            }
        };
    }
}
