package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.query.prescription.GetPrescriptionByIdQuery;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class GetPrescriptionByIdQueryHandler {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionEntity handle(GetPrescriptionByIdQuery query) {
        return prescriptionRepository.findById(query.getId())
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found with id " + query.getId()));
    }
}
