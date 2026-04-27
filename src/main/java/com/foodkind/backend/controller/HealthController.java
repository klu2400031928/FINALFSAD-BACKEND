package com.foodkind.backend.controller;

import com.foodkind.backend.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api")
public class HealthController {

    /**
     * GET /api/health
     * Public health check endpoint.
     */
    @GetMapping("/health")
    public ResponseEntity<ApiResponse<Map<String, Object>>> healthCheck() {
        log.debug("Health check endpoint called");
        Map<String, Object> healthData = Map.of(
                "status", "UP",
                "application", "Foodkind Backend",
                "timestamp", LocalDateTime.now().toString()
        );
        return ResponseEntity.ok(ApiResponse.success("Service is running", healthData));
    }
}
