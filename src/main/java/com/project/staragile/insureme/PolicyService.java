package com.project.staragile.insureme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PolicyService {

    @Autowired
    private PolicyRepository policyRepository;

    // Method to create and save a new policy
    public Policy createPolicy() {
        Policy policy = generateDummyPolicy();
        return policyRepository.save(policy);
    }

    // Method to update an existing policy by ID
    public Policy updatePolicy(int policyId, Policy updatedPolicy) {
        Optional<Policy> existingPolicyOpt = policyRepository.findById(policyId);
        if (existingPolicyOpt.isPresent()) {
            Policy existingPolicy = existingPolicyOpt.get();
            existingPolicy.setPolicyHolderName(updatedPolicy.getPolicyHolderName());
            existingPolicy.setPolicyType(updatedPolicy.getPolicyType());
            existingPolicy.setPolicyPrice(updatedPolicy.getPolicyPrice());
            existingPolicy.setPolicyStartDate(updatedPolicy.getPolicyStartDate());
            existingPolicy.setPolicyEndDate(updatedPolicy.getPolicyEndDate());
            return policyRepository.save(existingPolicy);
        } else {
            return null;  // Or throw exception if needed
        }
    }

    // Method to delete an existing policy by ID
    public boolean deletePolicy(int policyId) {
        Optional<Policy> existingPolicyOpt = policyRepository.findById(policyId);
        if (existingPolicyOpt.isPresent()) {
            policyRepository.delete(existingPolicyOpt.get());
            return true;
        } else {
            return false;  // Or throw exc
