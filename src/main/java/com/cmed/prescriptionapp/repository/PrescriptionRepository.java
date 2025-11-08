package com.cmed.prescriptionapp.repository;

import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long>, JpaSpecificationExecutor<PrescriptionEntity> {
}
