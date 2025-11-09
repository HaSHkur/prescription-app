package com.cmed.prescriptionapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table (name = "prescriptions")
public class PrescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patientName;

    @Column(nullable = false)
    private Integer patientAge;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date prescriptionDate;

    @Temporal(TemporalType.DATE)
    @Column
    private Date nextVisitDate;

    @Column
    private String patientGender;

    @Column
    private String diagnosis;

    @Column
    private String medicines;
}
