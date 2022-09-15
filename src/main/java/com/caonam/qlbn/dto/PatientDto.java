package com.caonam.qlbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDto {
    private UUID id;

    @NotEmpty(message = "không để trống")
    private String name;

    @NotEmpty(message = "không để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
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
}
