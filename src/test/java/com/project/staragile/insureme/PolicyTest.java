package com.project.staragile.insureme;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PolicyTest {

    @Test
    public void testPolicyGetterSetter() {
        Policy policy = new Policy();
        policy.setPolicyHolderName("Sreyansh Nandan");
        Assert.assertEquals(policy.getPolicyHolderName(), "Sreyansh Nandan");
    }
    
    @Test
    public void testCreatePolicy() {
        // Test code for creating policy using TestNG
        // Add your test logic here
        Assert.assertTrue(true);
    }

    @Test
    public void testViewPolicy() {
        // Test code for viewing policy using TestNG
        // Add your test logic here
        Assert.assertTrue(true);
    }

    @Test
    public void testUpdatePolicy() {
        // Test code for updating policy using TestNG
        // Add your test logic here
        Assert.assertTrue(true);
    }

    @Test
    public void testDeletePolicy() {
        // Test code for deleting policy using TestNG
        // Add your test logic here
        Assert.assertTrue(true);
    }
}
