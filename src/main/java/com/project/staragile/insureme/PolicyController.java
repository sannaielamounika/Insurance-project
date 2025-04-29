package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PolicyController {

    @Autowired
    private PolicyService policyService;

    // a. Create Policy - POST
    @PostMapping("/createPolicy")
    public Policy createPolicy(@RequestBody Policy policy) {
        return policyService.registerPolicy(policy);
    }

    // b. Update Policy - PUT
    @PutMapping("/updatePolicy/{policyId}")
    public Policy updatePolicy(@PathVariable int policyId, @RequestBody Policy updatedPolicy) {
        return policyService.updatePolicy(policyId, updatedPolicy);
    }

    // c. View Policy - GET
    @GetMapping("/viewPolicy/{policyId}")
    public Policy getPolicy(@PathVariable int policyId) {
        return policyService.getPolicyDetails(policyId);
    }

    // d. Delete Policy - DELETE
    @DeleteMapping("/deletePolicy/{policyId}")
    public String deletePolicy(@PathVariable int policyId) {
        policyService.deletePolicy(policyId);
        return "Policy with ID " + policyId + " deleted successfully.";
    }
}
