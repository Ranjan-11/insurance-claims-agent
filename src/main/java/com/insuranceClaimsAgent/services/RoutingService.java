package com.insuranceClaimsAgent.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class RoutingService {

    public String[] routeClaim(Map<String, Object> fields, List<String> missing) {

        String description = String.valueOf(fields.get("description")).toLowerCase();
        Integer damage = (Integer) fields.get("estimatedDamage");
        String claimType = String.valueOf(fields.get("claimType")).toLowerCase();

        if (!missing.isEmpty()) {
            return new String[]{"Manual Review", "Mandatory fields are missing"};
        }

        if (description.contains("fraud")
                || description.contains("staged")
                || description.contains("inconsistent")) {
            return new String[]{"Investigation Flag", "Suspicious keywords found in description"};
        }

        if ("injury".equals(claimType)) {
            return new String[]{"Specialist Queue", "Injury claim requires specialist handling"};
        }

        if (damage != null && damage < 25000) {
            return new String[]{"Fast-track", "Estimated damage is below threshold"};
        }

        return new String[]{"Manual Review", "Default routing applied"};
    }
}