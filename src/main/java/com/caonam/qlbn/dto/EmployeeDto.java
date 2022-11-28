package com.caonam.qlbn.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeDto {
    private UUID id;

    @NotEmpty(message = "không để trống")
    private String name;

    @NotEmpty(message = "không để trống")
    private String position;

    @NotEmpty(message = "không để trống")
    @Digits(integer = 10, fraction = 0)
    private String phone;

    @Email(message = "nhập đúng định dạng email")
    private String email;

//    private PrescriptionDto prescriptionDto;
//
//    private static ModelMapper modelMapper = new ModelMapper();

//    public static EmployeeDto toDto(Employee employee) {
//        EmployeeDto dto = modelMapper.map(employee, EmployeeDto.class);
//        dto.setPrescriptionDto(modelMapper.map(employee.getPrescription(), PrescriptionDto.class));
//        return dto;
//    }
}
