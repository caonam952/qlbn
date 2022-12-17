package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Patient;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDto {
    private UUID id;

    @NotEmpty(message = "không để trống")
    private String name;

    @NotNull(message = "không để trống")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate birth;

    @NotEmpty(message = "không để trống")
    private String sex;

    @NotEmpty(message = "không để trống")
    private String address;

    @NotEmpty(message = "không để trống")
    @Digits(integer = 10, fraction = 0)
    private String phone;

    @Email(message = "nhập đúng định dạng email")
    private String email;

    private String note;

    private Date createAt;

    private RecordDto recordDto;

    private List<PrescriptionDto> prescriptionDtos;

    public static PatientDto toDto(Patient patient) {
        PatientDto dto = new ModelMapper().map(patient, PatientDto.class);
        if (patient.getRecord() != null) {
            dto.setRecordDto(new ModelMapper().map(patient.getRecord(), RecordDto.class));
        }
        dto.setPrescriptionDtos(patient.getPrescriptions().stream().map(
                prescription -> {
                    return new ModelMapper().map(prescription, PrescriptionDto.class);
                }
        ).collect(Collectors.toList()));
        return dto;
    }
}
