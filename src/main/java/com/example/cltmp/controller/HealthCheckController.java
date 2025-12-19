package com.example.cltmp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    private String cachedRegion = null;

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        // 리전 정보는 변하지 않으므로 한 번만 조회하고 캐싱합니다.
        if (cachedRegion == null) {
            cachedRegion = getAzureRegion();
        }

        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("region", cachedRegion);
        
        return response;
    }

    private String getAzureRegion() {
        try {
            // Azure Instance Metadata Service URL
            String url = "http://169.254.169.254/metadata/instance/compute/location?api-version=2021-02-01&format=text";
            
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Metadata", "true"); // Azure IMDS 필수 헤더
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            return response.getBody();
        } catch (Exception e) {
            // 로컬 환경이거나 메타데이터 서비스를 호출할 수 없는 경우
            return "Local (or Unknown)";
        }
    }
}
