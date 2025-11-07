package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.command.prescription.CreatePrescriptionCommand;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreatePrescriptionCommandHandler {

    private final PrescriptionRepository prescriptionRepository;

    public Long handle(CreatePrescriptionCommand command) {
        PrescriptionEntity prescription = PrescriptionEntity.builder()
                .patientName(command.getPatientName())
                .patientAge(command.getPatientAge())
                .prescriptionDate(command.getPrescriptionDate())
                .nextVisitDate(command.getNextVisitDate())
                .patientGender(command.getPatientGender())
                .diagnosis(command.getDiagnosis())
                .medicines(command.getMedicines())
                .build();

        prescription = prescriptionRepository.save(prescription);
        return prescription.getId();
    }
}
