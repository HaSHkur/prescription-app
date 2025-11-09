package com.cmed.prescriptionapp.repository;

import com.cmed.prescriptionapp.domain.PrescriptionSpec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PrescriptionSpecification {

    public Specification<PrescriptionSpec> findByCriteria(String patientName, Integer patientAge, Date fromDate, Date toDate) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (patientName != null && !patientName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("patientName")), "%" + patientName.toLowerCase() + "%"));
            }

            if (patientAge != null) {
                predicates.add(criteriaBuilder.equal(root.get("patientAge"), patientAge));
            }

            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("prescriptionDate"), fromDate));
            }

            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("prescriptionDate"), toDate));
            }

            // This part is important for mapping to a DTO projection if needed, and to avoid N+1 issues
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}