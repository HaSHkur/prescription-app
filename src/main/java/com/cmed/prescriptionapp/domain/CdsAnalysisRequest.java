package com.cmed.prescriptionapp.domain;

import lombok.Data;
import java.util.List;

@Data
public class CdsAnalysisRequest {
    private int patientAge;
    private String patientGender;
    private String diagnosis;
    private List<String> medicines;
}
