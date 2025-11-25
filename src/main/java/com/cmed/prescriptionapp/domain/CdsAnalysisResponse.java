package com.cmed.prescriptionapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CdsAnalysisResponse {
    private String analysisText;
    private String disclaimer;
}
