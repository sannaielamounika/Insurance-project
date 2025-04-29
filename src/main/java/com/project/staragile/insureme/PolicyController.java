package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    // Create Policy
    @PostMapping("/createPolicy")
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.createPolicy(policy);
    }

    // Update Policy
    @PutMapping("/updatePolicy/{policyId}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable int policyId, @RequestBody Policy updatedPolicy) {
        Optional<Policy> policy = policyService.updatePolicy(policyId, updatedPolicy);
        return policy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // View Policy
    @GetMapping("/viewPolicy/{policyId}")
    public ResponseEntity<Policy> viewPolicy(@PathVariable int policyId) {
        Optional<Policy> policy = policyService.getPolicyById(policyId);
        return policy.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete Policy
    @DeleteMapping("/deletePolicy/{policyId}")
    public ResponseEntity<String> deletePolicy(@PathVariable int policyId) {
        boolean deleted = policyService.deletePolicy(policyId);
        if (deleted) {
            return ResponseEntity.ok("Policy deleted successfully.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
