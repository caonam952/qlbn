package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Medicine;
import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.entities.PrescriptionDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PrescriptionDetailDto {

    private UUID id;

    private int amount;

    private String dosage;

    private PrescriptionDto prescriptionDto;

    private MedicineDto medicineDto;

    private Date createAt;

    public static PrescriptionDetailDto toDto(PrescriptionDetail prescriptionDetail) {
        PrescriptionDetailDto dto = new ModelMapper().map(prescriptionDetail, PrescriptionDetailDto.class);
        dto.setPrescriptionDto(new ModelMapper().map(prescriptionDetail.getPrescription(), PrescriptionDto.class));
        dto.setMedicineDto(new ModelMapper().map(prescriptionDetail.getMedicine(), MedicineDto.class));
        return dto;
    }

}
