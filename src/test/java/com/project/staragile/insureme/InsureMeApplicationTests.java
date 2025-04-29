package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InsureMeApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    private Policy testPolicy;

    @BeforeMethod
    public void setUp() {
        testPolicy = new Policy(0, "Sreyansh Nandan", "Travel Insurance", 5000.0, "2024-05-01", "2025-05-01");
    }

    @Test
    public void testCreatePolicy() {
        ResponseEntity<Policy> response = restTemplate.postForEntity("/policy/createPolicy", testPolicy, Policy.class);
        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        assertNotNull(response.getBody().getPolicyId());
    }

    @Test
    public void testViewPolicy() {
        Policy createdPolicy = restTemplate.postForObject("/policy/createPolicy", testPolicy, Policy.class);
        ResponseEntity<Policy> response = restTemplate.getForEntity("/policy/viewPolicy/" + createdPolicy.getPolicyId(), Policy.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(response.getBody());
    }

    @Test
    public void testUpdatePolicy() {
        Policy createdPolicy = restTemplate.postForObject("/policy/createPolicy", testPolicy, Policy.class);
        createdPolicy.setPolicyHolderName("Sreyansh Nandan Updated");
        restTemplate.put("/policy/updatePolicy/" + createdPolicy.getPolicyId(), createdPolicy);
        ResponseEntity<Policy> response = restTemplate.getForEntity("/policy/viewPolicy/" + createdPolicy.getPolicyId(), Policy.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(response.getBody().getPolicyHolderName(), "Sreyansh Nandan Updated");
    }

    @Test
    public void testDeletePolicy() {
        Policy createdPolicy = restTemplate.postForObject("/policy/createPolicy", testPolicy, Policy.class);
        restTemplate.delete("/policy/deletePolicy/" + createdPolicy.getPolicyId());
        ResponseEntity<Policy> response = restTemplate.getForEntity("/policy/viewPolicy/" + createdPolicy.getPolicyId(), Policy.class);
        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
