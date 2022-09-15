package com.caonam.qlbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RecordDto {
    private UUID id;

    @NotEmpty(message = "không để trống")
    private String medicalHistory;

    private String productInUse;

    @NotEmpty(message = "không để trống")
    private String diagnose;

    @NotEmpty(message = "không để trống")
    private String result;

    @NotEmpty(message = "không để trống")
    private String regimen;

    private String preImage;

    private String afterImage;

    private PatientDto patientDto;

}
