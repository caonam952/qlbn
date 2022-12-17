package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Record;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class RecordDto {
    private UUID id;

//    @NotEmpty(message = "không để trống")
    private String medicalHistory;

    private String productInUse;

//    @NotEmpty(message = "không để trống")
    private String diagnose;

//    @NotEmpty(message = "không để trống")
    private String result;

//    @NotEmpty(message = "không để trống")
    private String regimen;

    private String preImage;

    private String afterImage;

    private Date createAt;

    private PatientDto patientDto;

    public static RecordDto toDto(Record record) {
        RecordDto dto = new ModelMapper().map(record, RecordDto.class);
        dto.setPatientDto(new ModelMapper().map(record.getPatient(), PatientDto.class));
        return dto;
    }

}
