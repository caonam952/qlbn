package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class MedicineDto {

    private int id;

    @NotEmpty(message = "không để trống")
    private String name;

    @NotEmpty(message = "không để trống")
    private String origin;

    @NotEmpty(message = "không để trống")
    private String uni;

    @NotEmpty(message = "không để trống")
    private int amount;

    @NotEmpty(message = "không để trống")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Future
    private LocalDate expDate;

    @NotEmpty(message = "không để trống")
    private Double importPrice;

    @NotEmpty(message = "không để trống")
    private Double price;

    @NotEmpty(message = "không để trống")
    private String manual;

    private String note;

}
