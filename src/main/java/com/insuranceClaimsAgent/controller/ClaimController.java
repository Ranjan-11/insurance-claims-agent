package com.insuranceClaimsAgent.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insuranceClaimsAgent.model.ClaimResponse;
import com.insuranceClaimsAgent.services.FnolExtractionService;
import com.insuranceClaimsAgent.services.RoutingService;
import com.insuranceClaimsAgent.services.ValidationService;
import com.insuranceClaimsAgent.util.PdfUtil;
import com.insuranceClaimsAgent.util.TxtUtil;





@RestController
@RequestMapping("/claim")
public class ClaimController {

    private final FnolExtractionService extractionService;
    private final ValidationService validationService;
    private final RoutingService routingService;

    public ClaimController(FnolExtractionService extractionService, ValidationService validationService,
			RoutingService routingService) {
		super();
		this.extractionService = extractionService;
		this.validationService = validationService;
		this.routingService = routingService;
	}

    @GetMapping("/process")
    public ClaimResponse processClaim() throws IOException {

        String fileName = "fnol_2.txt"; // change to fnol_1.pdf when needed

        String text;

        if (fileName.toLowerCase().endsWith(".pdf")) {
            text = PdfUtil.readPdf(fileName);
        } else if (fileName.toLowerCase().endsWith(".txt")) {
            text = TxtUtil.readTxt(fileName);
        } else {
            throw new IllegalArgumentException("Unsupported file type");
        }

        Map<String, Object> fields = extractionService.extractAllFields(text);
        List<String> missing = validationService.checkMissingFields(fields);
        String[] route = routingService.routeClaim(fields, missing);

        ClaimResponse response = new ClaimResponse();
        response.setExtractedFields(fields);
        response.setMissingFields(missing);
        response.setRecommendedRoute(route[0]);
        response.setReasoning(route[1]);

        return response;
    }
    
   
    

    
   
    
}
