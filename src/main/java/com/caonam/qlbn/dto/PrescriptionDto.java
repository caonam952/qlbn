package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.entities.PrescriptionDetail;
import com.caonam.qlbn.entities.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PrescriptionDto {
    private UUID id;

    @NotNull(message = "không để trống")
    private LocalDate prescriptionDate;

    @NotNull(message = "không để trống")
    @Future
    private LocalDate appointmentDate;

    private PatientDto patientDto;

    private EmployeeDto employeeDto;

    private List<PrescriptionDetailDto> prescriptionDetailDtos;

    public static PrescriptionDto toDto(Prescription prescription) {
        PrescriptionDto dto = new ModelMapper().map(prescription, PrescriptionDto.class);
        dto.setPatientDto(new ModelMapper().map(prescription.getPatient(), PatientDto.class));
        dto.setEmployeeDto(new ModelMapper().map(prescription.getEmployee(), EmployeeDto.class));
        dto.setPrescriptionDetailDtos(prescription.getPrescriptionDetails().stream().map(
                prescriptionDetail -> {
                    return new ModelMapper().map(prescriptionDetail, PrescriptionDetailDto.class);
                }).collect(Collectors.toList()));
        return dto;
    }
}
