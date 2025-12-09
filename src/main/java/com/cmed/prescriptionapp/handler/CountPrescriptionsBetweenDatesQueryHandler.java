package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.domain.PrescriptionCountResponse;
import com.cmed.prescriptionapp.query.prescription.CountPrescriptionsBetweenDatesQuery;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountPrescriptionsBetweenDatesQueryHandler {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionCountResponse handle(CountPrescriptionsBetweenDatesQuery query) {
        long count = prescriptionRepository.countByPrescriptionDateBetween(query.getFromDate(), query.getToDate());
        return new PrescriptionCountResponse(null, count);
    }
}
