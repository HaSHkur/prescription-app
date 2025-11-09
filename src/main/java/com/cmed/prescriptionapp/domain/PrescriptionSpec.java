package com.cmed.prescriptionapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrescriptionSpec {
    public String patientName;
    public String patientAge;
    public String fromDate;
    public String toDate;
}
