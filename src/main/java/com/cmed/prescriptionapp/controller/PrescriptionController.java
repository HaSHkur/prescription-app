package com.cmed.prescriptionapp.controller;

import com.cmed.prescriptionapp.command.prescription.CreatePrescriptionCommand;
import com.cmed.prescriptionapp.command.prescription.DeletePrescriptionCommand;
import com.cmed.prescriptionapp.command.prescription.UpdatePrescriptionCommand;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.handler.*;
import com.cmed.prescriptionapp.query.prescription.GetAllPrescriptionsQuery;
import com.cmed.prescriptionapp.query.prescription.GetPrescriptionByIdQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@AllArgsConstructor
public class PrescriptionController {

    private final CreatePrescriptionCommandHandler createPrescriptionCommandHandler;
    private final UpdatePrescriptionCommandHandler updatePrescriptionCommandHandler;
    private final DeletePrescriptionCommandHandler deletePrescriptionCommandHandler;
    private final GetPrescriptionByIdQueryHandler getPrescriptionByIdQueryHandler;
    private final GetAllPrescriptionsQueryHandler getAllPrescriptionsQueryHandler;

    @PostMapping
    public ResponseEntity<Long> createPrescription(@RequestBody CreatePrescriptionCommand command) {
        Long id = createPrescriptionCommandHandler.handle(command);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> updatePrescription(@RequestBody UpdatePrescriptionCommand command) {
        updatePrescriptionCommandHandler.handle(command);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrescription(@PathVariable Long id) {
        deletePrescriptionCommandHandler.handle(new DeletePrescriptionCommand(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrescriptionEntity> getPrescriptionById(@PathVariable Long id) {
        PrescriptionEntity prescription = getPrescriptionByIdQueryHandler.handle(new GetPrescriptionByIdQuery(id));
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionEntity>> getAllPrescriptions() {
        List<PrescriptionEntity> prescriptions = getAllPrescriptionsQueryHandler.handle(new GetAllPrescriptionsQuery());
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }
}
