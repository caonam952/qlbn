package com.caonam.qlbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RecordDto {
    private int id;

    @NotBlank(message = "không để trống")
    private String medicalHistory;

    private String productInUse;

    @NotBlank(message = "không để trống")
    private String diagnose;

    @NotBlank(message = "không để trống")
    private String result;

    @NotBlank(message = "không để trống")
    private String regimen;

    private String preImage;

    private String afterImage;

    private PatientDto patientDto;

}
