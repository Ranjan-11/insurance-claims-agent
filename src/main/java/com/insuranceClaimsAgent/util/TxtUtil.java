package com.insuranceClaimsAgent.util;


import java.io.IOException;
import org.springframework.core.io.ClassPathResource;

public class TxtUtil {

    public static String readTxt(String fileName) throws IOException {
        ClassPathResource resource =
                new ClassPathResource("data/" + fileName);

        return new String(resource.getInputStream().readAllBytes());
    }
}
