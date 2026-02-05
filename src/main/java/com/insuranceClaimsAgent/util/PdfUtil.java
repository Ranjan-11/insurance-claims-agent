package com.insuranceClaimsAgent.util;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.core.io.ClassPathResource;


public class PdfUtil {

	public static String readPdf(String fileName) {

	    try {
	        ClassPathResource resource =
	                new ClassPathResource("data/" + fileName);

	        PDDocument document = PDDocument.load(resource.getInputStream());
	        PDFTextStripper stripper = new PDFTextStripper();
	        String text = stripper.getText(document);
	        document.close();

	        return text;

	    } catch (Exception e) {
	        // Empty / invalid / scanned PDF
	        return "";
	    }
	}


}
