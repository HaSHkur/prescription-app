package com.cmed.prescriptionapp.controller;

import com.cmed.prescriptionapp.domain.ConfigurationUpdateRequest;
import com.cmed.prescriptionapp.entity.ConfigurationEntity;
import com.cmed.prescriptionapp.repository.ConfigurationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/config")
@RequiredArgsConstructor
public class ConfigurationController {

    private final ConfigurationRepository configurationRepository;
    private static final String API_KEY_NAME = "GEMINI_API_KEY";

    @PostMapping("/gemini-key")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> setGeminiApiKey(@RequestBody ConfigurationUpdateRequest request) {
        ConfigurationEntity apiKey = new ConfigurationEntity(API_KEY_NAME, request.getValue());
        configurationRepository.save(apiKey);
        return ResponseEntity.ok("Gemini API Key updated successfully.");
    }
}
