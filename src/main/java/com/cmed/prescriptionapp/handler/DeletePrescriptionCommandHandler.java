package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.command.prescription.DeletePrescriptionCommand;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DeletePrescriptionCommandHandler {

    private final PrescriptionRepository prescriptionRepository;

    public void handle(DeletePrescriptionCommand command) {
        prescriptionRepository.deleteById(command.getId());
    }
}
