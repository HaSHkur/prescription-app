package com.cmed.prescriptionapp.query.prescription;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@Getter
@AllArgsConstructor
public class CountPrescriptionsBetweenDatesQuery {
    private final Date fromDate;
    private final Date toDate;
}
