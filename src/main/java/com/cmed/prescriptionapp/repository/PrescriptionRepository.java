package com.cmed.prescriptionapp.repository;

import com.cmed.prescriptionapp.domain.PrescriptionCountResponse;
import com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long>, JpaSpecificationExecutor<PrescriptionEntity> {

    @Query("SELECT new com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse(p.id, p.patientName, p.patientAge, p.prescriptionDate) FROM PrescriptionEntity p")
    Page<PrescriptionSummaryResponse> findAllProjectedBy(Specification<PrescriptionEntity> spec, Pageable pageable);

    @Query("SELECT count(p) FROM PrescriptionEntity p WHERE p.prescriptionDate = :date")
    long countByPrescriptionDate(@Param("date") Date date);

    @Query("SELECT new com.cmed.prescriptionapp.domain.PrescriptionCountResponse(p.prescriptionDate, COUNT(p)) FROM PrescriptionEntity p GROUP BY p.prescriptionDate")
    List<PrescriptionCountResponse> countPrescriptionsGroupedByDate();
}
