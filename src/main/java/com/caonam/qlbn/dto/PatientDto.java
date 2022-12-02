package com.caonam.qlbn.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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

    @Column(name = "note")
    private String note;

    private Date createAt;
//    private RecordDto recordDto;

    private List<PrescriptionDto> prescriptionDtos;
}
