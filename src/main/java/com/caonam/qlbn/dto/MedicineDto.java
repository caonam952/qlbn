package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MedicineDto {

    private UUID id;

    @NotEmpty(message = "không để trống")
    private String name;

    @NotEmpty(message = "không để trống")
    private String origin;

    @NotEmpty(message = "không để trống")
    private String uni;

    @NotNull(message = "không để trống")
    private int amount;

    @NotNull(message = "không để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate importDate;

    @NotNull(message = "không để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Future
    private LocalDate expDate;

    @NotNull(message = "không để trống")
    private Double importPrice;

    @NotNull(message = "không để trống")
    private Double price;

    @NotEmpty(message = "không để trống")
    private String manual;

    private String note;

//    private PrescriptionDto prescriptionDto;
}
