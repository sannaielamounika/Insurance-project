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

    @Override
    public void run(String... args) throws Exception {
        policyRepository.save(new Policy(0, "Alice", "Health", 12000, "01-Jan-2023", "31-Dec-2023"));
        policyRepository.save(new Policy(0, "Bob", "Life", 15000, "01-Feb-2023", "31-Jan-2024"));
        policyRepository.save(new Policy(0, "Charlie", "Car", 8000, "15-Mar-2023", "14-Mar-2024"));
    }
}
