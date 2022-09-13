package com.caonam.qlbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MedicineDto {

    private int id;

    @NotBlank(message = "không để trống")
    private int name;

    @NotBlank(message = "không để trống")
    private String origin;

    @NotBlank(message = "không để trống")
    private String uni;

    @NotBlank(message = "không để trống")
    private int amount;

    @NotBlank(message = "không để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Future
    private LocalDate expDate;

    @NotBlank(message = "không để trống")
    private Double importPrice;

    @NotBlank(message = "không để trống")
    private Double price;

    @NotBlank(message = "không để trống")
    private String manual;

    private String note;
}
