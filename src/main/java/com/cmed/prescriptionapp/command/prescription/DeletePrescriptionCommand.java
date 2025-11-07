package com.cmed.prescriptionapp.command.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeletePrescriptionCommand {
    private Long id;
}
