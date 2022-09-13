package com.caonam.qlbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PatientDto {
    private int uuid;

    @NotBlank(message = "không để trống")
    private String name;

    @NotBlank(message = "không để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate birth;

    @NotBlank(message = "không để trống")
    private String sex;

    @NotBlank(message = "không để trống")
    private String address;

    @NotBlank(message = "không để trống")
    private String phone;

    @Email(message = "nhập đúng định dạng email")
    private String email;

    @Column(name = "note")
    private String note;
}
