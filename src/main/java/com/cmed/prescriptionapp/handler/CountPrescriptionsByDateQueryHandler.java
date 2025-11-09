package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.domain.PrescriptionCountResponse;
import com.cmed.prescriptionapp.query.prescription.CountPrescriptionsByDateQuery;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CountPrescriptionsByDateQueryHandler {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionCountResponse handle(CountPrescriptionsByDateQuery query) {
        long count = prescriptionRepository.countByPrescriptionDate(query.getDate());
        return new PrescriptionCountResponse(query.getDate(), count);
    }
}
