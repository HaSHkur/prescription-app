package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse;
import com.cmed.prescriptionapp.query.prescription.GetAllPrescriptionsQuery;
import com.cmed.prescriptionapp.service.PrescriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GetAllPrescriptionsQueryHandler {

    private final PrescriptionService prescriptionService;

    public Page<PrescriptionSummaryResponse> handle(GetAllPrescriptionsQuery query) {
        return prescriptionService.getAllPrescriptions(
                query.getPatientName(),
                query.getPatientAge(),
                query.getFromDate(),
                query.getToDate(),
                query.getPage(),
                query.getSize()
        );
    }
}