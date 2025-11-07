package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.command.prescription.UpdatePrescriptionCommand;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;

@Service
@AllArgsConstructor
public class UpdatePrescriptionCommandHandler {

    private final PrescriptionRepository prescriptionRepository;

    public void handle(UpdatePrescriptionCommand command) {
        PrescriptionEntity prescription = prescriptionRepository.findById(command.getId())
                .orElseThrow(() -> new EntityNotFoundException("Prescription not found with id " + command.getId()));

        prescription.setPatientName(command.getPatientName());
        prescription.setPatientAge(command.getPatientAge());
        prescription.setPrescriptionDate(command.getPrescriptionDate());
        prescription.setNextVisitDate(command.getNextVisitDate());
        prescription.setPatientGender(command.getPatientGender());
        prescription.setDiagnosis(command.getDiagnosis());
        prescription.setMedicines(command.getMedicines());

        prescriptionRepository.save(prescription);
    }
}
