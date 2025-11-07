package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.query.prescription.GetAllPrescriptionsQuery;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetAllPrescriptionsQueryHandler {
    private final PrescriptionRepository prescriptionRepository;

    public List<PrescriptionEntity> handle(GetAllPrescriptionsQuery query) {
        return prescriptionRepository.findAll();
    }
}
