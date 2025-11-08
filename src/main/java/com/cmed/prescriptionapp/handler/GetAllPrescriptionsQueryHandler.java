package com.cmed.prescriptionapp.handler;

import com.cmed.prescriptionapp.domain.PrescriptionSummaryResponse;
import com.cmed.prescriptionapp.entity.PrescriptionEntity;
import com.cmed.prescriptionapp.mapper.PrescriptionMapper;
import com.cmed.prescriptionapp.query.prescription.GetAllPrescriptionsQuery;
import com.cmed.prescriptionapp.repository.PrescriptionRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAllPrescriptionsQueryHandler {
    private final PrescriptionRepository prescriptionRepository;

    public Page<PrescriptionSummaryResponse> handle(GetAllPrescriptionsQuery query) {
        Pageable pageable = PageRequest.of(query.getPage(), query.getSize());

        Specification<PrescriptionEntity> spec = (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (query.getPatientName() != null) {
                predicates.add(cb.like(root.get("patientName"), "%" + query.getPatientName() + "%"));
            }

            if (query.getPatientAge() != null) {
                predicates.add(cb.equal(root.get("patientAge"), query.getPatientAge()));
            }

            if (query.getPrescriptionDate() != null) {
                predicates.add(cb.equal(root.get("prescriptionDate"), query.getPrescriptionDate()));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<PrescriptionEntity> page = prescriptionRepository.findAll(spec, pageable);
        List<PrescriptionSummaryResponse> summaries = page.getContent().stream()
                .map(PrescriptionMapper::toSummaryResponse)
                .collect(Collectors.toList());

        return new PageImpl<>(summaries, pageable, page.getTotalElements());
    }
}
