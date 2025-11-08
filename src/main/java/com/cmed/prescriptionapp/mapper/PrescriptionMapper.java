package com.cmed.prescriptionapp.mapper;

import com.cmed.prescriptionapp.domain.PrescriptionDetailsResponse;
import com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;

public class PrescriptionMapper {

    public static PrescriptionDetailsResponse toDetailsResponse(PrescriptionEntity entity) {
        return PrescriptionDetailsResponse.builder()
                .id(entity.getId())
                .patientName(entity.getPatientName())
                .patientAge(entity.getPatientAge())
                .prescriptionDate(entity.getPrescriptionDate())
                .nextVisitDate(entity.getNextVisitDate())
                .patientGender(entity.getPatientGender())
                .diagnosis(entity.getDiagnosis())
                .medicines(entity.getMedicines())
                .build();
    }

    public static PrescriptionSummaryResponse toSummaryResponse(PrescriptionEntity entity) {
        return PrescriptionSummaryResponse.builder()
                .id(entity.getId())
                .patientName(entity.getPatientName())
                .patientAge(entity.getPatientAge())
                .prescriptionDate(entity.getPrescriptionDate())
                .build();
    }
}
