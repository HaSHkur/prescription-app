package com.cmed.prescriptionapp.controller;

import com.cmed.prescriptionapp.command.prescription.CreatePrescriptionCommand;
import com.cmed.prescriptionapp.command.prescription.DeletePrescriptionCommand;
import com.cmed.prescriptionapp.command.prescription.UpdatePrescriptionCommand;
import com.cmed.prescriptionapp.domain.PrescriptionDetailsResponse;
import com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse;
import com.cmed.prescriptionapp.handler.*;
import com.cmed.prescriptionapp.query.prescription.GetAllPrescriptionsQuery;
import com.cmed.prescriptionapp.query.prescription.GetPrescriptionByIdQuery;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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
    public ResponseEntity<PrescriptionDetailsResponse> getPrescriptionById(@PathVariable Long id) {
        PrescriptionDetailsResponse prescription = getPrescriptionByIdQueryHandler.handle(new GetPrescriptionByIdQuery(id));
        return new ResponseEntity<>(prescription, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<PrescriptionSummaryResponse>> getAllPrescriptions(
            @RequestParam(required = false) String patientName,
            @RequestParam(required = false) Integer patientAge,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date prescriptionDate,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        GetAllPrescriptionsQuery query = new GetAllPrescriptionsQuery(patientName, patientAge, prescriptionDate, page, size);
        Page<PrescriptionSummaryResponse> prescriptions = getAllPrescriptionsQueryHandler.handle(query);
        return new ResponseEntity<>(prescriptions, HttpStatus.OK);
    }
}
