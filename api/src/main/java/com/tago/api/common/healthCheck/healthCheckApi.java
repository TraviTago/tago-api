package com.tago.api.common.healthCheck;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class healthCheckApi {

    @Operation(hidden = true)
    @GetMapping("/health")
    public String healthCheck() {
        return "healthy";
    }
}
