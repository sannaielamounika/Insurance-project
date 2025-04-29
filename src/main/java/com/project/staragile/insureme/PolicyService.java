package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }

    public Optional<Policy> getPolicyById(int policyId) {
        return policyRepository.findById(policyId);
    }

    public Optional<Policy> updatePolicy(int policyId, Policy updatedPolicy) {
        return policyRepository.findById(policyId).map(policy -> {
            policy.setPolicyHolderName(updatedPolicy.getPolicyHolderName());
            policy.setPolicyType(updatedPolicy.getPolicyType());
            policy.setPolicyPrice(updatedPolicy.getPolicyPrice());
            policy.setPolicyStartDate(updatedPolicy.getPolicyStartDate());
            policy.setPolicyEndDate(updatedPolicy.getPolicyEndDate());
            return policyRepository.save(policy);
        });
    }

    public boolean deletePolicy(int policyId) {
        if (policyRepository.existsById(policyId)) {
            policyRepository.deleteById(policyId);
            return true;
        }
        return false;
    }
}
