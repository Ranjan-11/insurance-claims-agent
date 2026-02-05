package com.insuranceClaimsAgent.services;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class FnolExtractionService {

    public Map<String, Object> extractAllFields(String text) {

        Map<String, Object> fields = new HashMap<>();

        // Policy Information
        fields.put("policyNumber", find(text, "POLICY NUMBER"));
        fields.put("policyHolderName", find(text, "NAME OF INSURED"));
        fields.put("policyEffectiveDates", "Not Available");

        // Incident Information
        fields.put("incidentDate", find(text, "DATE OF LOSS"));
        fields.put("incidentTime", find(text, "TIME"));
        fields.put("location", find(text, "LOCATION OF LOSS"));
        fields.put("description", find(text, "DESCRIPTION OF ACCIDENT"));

        // Involved Parties
        fields.put("claimant", find(text, "INSURED"));
        fields.put("thirdParties", find(text, "OTHER VEHICLE"));
        fields.put("contactDetails", find(text, "PHONE"));

        // Asset Details
        fields.put("assetType", "Vehicle");
        fields.put("assetId", find(text, "V.I.N."));
        fields.put("estimatedDamage", extractAmount(text));

        // Other Mandatory Fields
        fields.put("claimType", detectClaimType(text));
        fields.put("attachments", "FNOL PDF");
        fields.put("initialEstimate", extractAmount(text));

        return fields;
    }

    private String find(String text, String key) {
        Pattern p = Pattern.compile(key + ".*", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        return m.find() ? m.group() : null;
    }

    private Integer extractAmount(String text) {
        Pattern p = Pattern.compile("ESTIMATE AMOUNT.*?(\\d+)", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(text);
        return m.find() ? Integer.parseInt(m.group(1)) : null;
    }

    private String detectClaimType(String text) {
        return text.toLowerCase().contains("injury") ? "injury" : "vehicle";
    }
}