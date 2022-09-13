package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Prescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeDto {
    private int id;

    @NotBlank(message = "không để trống")
    private String name;

    @NotBlank(message = "không để trống")
    private String position;
    private PrescriptionDto prescriptionDto;
}
