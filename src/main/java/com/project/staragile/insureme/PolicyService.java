package com.project.staragile.insureme;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy registerPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Policy getPolicyDetails(int policyId) {
        return policyRepository.findById(policyId).orElse(null);
    }

    public Policy updatePolicy(int policyId, Policy updatedPolicy) {
        Optional<Policy> optionalPolicy = policyRepository.findById(policyId);
        if (optionalPolicy.isPresent()) {
            Policy existingPolicy = optionalPolicy.get();
            existingPolicy.setPolicyHolderName(updatedPolicy.getPolicyHolderName());
            existingPolicy.setPolicyType(updatedPolicy.getPolicyType());
            existingPolicy.setPolicyPrice(updatedPolicy.getPolicyPrice());
            existingPolicy.setPolicyStartDate(updatedPolicy.getPolicyStartDate());
            existingPolicy.setPolicyEndDate(updatedPolicy.getPolicyEndDate());
            return policyRepository.save(existingPolicy);
        } else {
            return null;
        }
    }

    public void deletePolicy(int policyId) {
        policyRepository.deleteById(policyId);
    }
}

