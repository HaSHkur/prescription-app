package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.domain.PrescriptionCountResponse;
import com.cmed.prescriptionapp.query.prescription.CountPrescriptionsGroupedByDateQuery;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CountPrescriptionsGroupedByDateQueryHandler {

    private final PrescriptionRepository prescriptionRepository;

    public List<PrescriptionCountResponse> handle(CountPrescriptionsGroupedByDateQuery query) {
        return prescriptionRepository.countPrescriptionsGroupedByDate();
    }
}
