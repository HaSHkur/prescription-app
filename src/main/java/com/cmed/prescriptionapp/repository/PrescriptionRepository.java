package com.cmed.prescriptionapp.repository;

import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {
}
