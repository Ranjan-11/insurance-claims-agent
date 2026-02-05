package com.insuranceClaimsAgent.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public List<String> checkMissingFields(Map<String, Object> fields) {

        List<String> mandatory = List.of(
                "policyNumber",
                "incidentDate",
                "description",
                "estimatedDamage",
                "claimType",
                "initialEstimate"
        );

        List<String> missing = new ArrayList<>();

        for (String field : mandatory) {
            if (fields.get(field) == null) {
                missing.add(field);
            }
        }
        return missing;
    }
}