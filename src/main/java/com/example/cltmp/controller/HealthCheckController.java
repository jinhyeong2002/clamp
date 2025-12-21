package com.example.cltmp.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HealthCheckController {

    @Value("${REGION_NAME:unknown}")
    private String region;

    @Value("${INSTANCE_NAME:unknown}")
    private String instance;

    @GetMapping("/health")
    public Map<String, String> healthCheck() {
        Map<String, String> response = new HashMap<>();
        response.put("status", "OK");
        response.put("region", region);
        response.put("instance", instance);
        return response;
    }
}
