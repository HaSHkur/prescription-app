package com.cmed.prescriptionapp.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PrescriptionDetailsResponse {
    private Long id;
    private String patientName;
    private Integer patientAge;
    private Date prescriptionDate;
    private Date nextVisitDate;
    private String patientGender;
    private String diagnosis;
    private String medicines;
}
