package com.insuranceClaimsAgent.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class ClaimResponse {

    private Map<String, Object> extractedFields;
    private List<String> missingFields;
    private String recommendedRoute;
    private String reasoning;
}
