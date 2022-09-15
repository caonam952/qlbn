package com.caonam.qlbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PrescriptionDto {
    private UUID id;

    @NotEmpty(message = "không để trống")
    private LocalDate prescriptionDate;

    @NotEmpty(message = "không để trống")
    @Future
    private LocalDate appointmentDate;

    private int amount;

    private String dosage;

    private PatientDto patientDto;

    private EmployeeDto employeeDto;

    private List<MedicineDto> medicineDtos;

}
