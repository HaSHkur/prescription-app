package com.cmed.prescriptionapp.query.prescription;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class GetAllPrescriptionsQuery {
    private String patientName;
    private Integer patientAge;
    private Date prescriptionDate;
    private int page;
    private int size;
}
