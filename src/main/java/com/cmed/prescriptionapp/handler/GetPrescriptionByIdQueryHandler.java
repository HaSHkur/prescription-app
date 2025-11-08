package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.domain.PrescriptionDetailsResponse;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.mapper.PrescriptionMapper;
import com.cmed.prescriptionapp.query.prescription.GetPrescriptionByIdQuery;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPrescriptionByIdQueryHandler {

    private final PrescriptionRepository prescriptionRepository;

    public PrescriptionDetailsResponse handle(GetPrescriptionByIdQuery query) {
        PrescriptionEntity prescription = prescriptionRepository.findById(query.getId())
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found with id " + query.getId()));
        return PrescriptionMapper.toDetailsResponse(prescription);
    }
}
