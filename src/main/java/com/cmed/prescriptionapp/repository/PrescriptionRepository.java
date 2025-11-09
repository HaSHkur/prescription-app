package com.cmed.prescriptionapp.repository;

import com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse;
import com.cmed.prescriptionapp.domain.PrescriptionSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PrescriptionRepository extends JpaRepository<PrescriptionSpec, Long>, JpaSpecificationExecutor<PrescriptionSpec> {

    // Using a constructor expression for DTO projection is highly efficient.
    @Query("SELECT new com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse(p.id, p.patientName, p.patientAge, p.prescriptionDate) FROM Prescription p")
    Page<PrescriptionSummaryResponse> findAllProjectedBy(Specification<PrescriptionSpec> spec, Pageable pageable);

}