package com.cmed.prescriptionapp.controller;

import com.cmed.prescriptionapp.domain.CdsAnalysisRequest;
import com.cmed.prescriptionapp.domain.CdsAnalysisResponse;
import com.cmed.prescriptionapp.service.CdsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/cds")
@RequiredArgsConstructor
public class CdsController {

    private final CdsService cdsService;

    @PostMapping("/analyze")
    public ResponseEntity<CdsAnalysisResponse> analyzePrescription(@RequestBody CdsAnalysisRequest request) {
        try {
            CdsAnalysisResponse response = cdsService.analyzePrescription(request);
            return ResponseEntity.ok(response);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CdsAnalysisResponse("Error communicating with AI service.", ""));
        }
    }
}
