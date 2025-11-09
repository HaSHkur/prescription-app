package com.cmed.prescriptionapp.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PrescriptionCountResponse {
    private Date date;
    private long count;
}
