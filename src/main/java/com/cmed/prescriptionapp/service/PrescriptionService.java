package com.cmed.prescriptionapp.service;

import com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.mapper.PrescriptionMapper;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import com.cmed.prescriptionapp.repository.PrescriptionSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionSpecification prescriptionSpecification;

    public Page<PrescriptionSummaryResponse> getAllPrescriptions(String patientName, Integer patientAge, Date fromDate, Date toDate, int page, int size) {
        Date from = fromDate;
        Date to = toDate;

        if (from == null && to == null) {
            LocalDate now = LocalDate.now();
            from = Date.from(now.with(TemporalAdjusters.firstDayOfMonth()).atStartOfDay(ZoneId.systemDefault()).toInstant());
            to = Date.from(now.with(TemporalAdjusters.lastDayOfMonth()).atTime(23, 59, 59).atZone(ZoneId.systemDefault()).toInstant());
        }

        Specification<PrescriptionEntity> spec = prescriptionSpecification.findByCriteria(patientName, patientAge, from, to);

        Pageable pageable = PageRequest.of(page, size);

        Page<PrescriptionEntity> entities = prescriptionRepository.findAll(spec, pageable);

        return entities.map(PrescriptionMapper::toSummaryResponse);
    }
}
