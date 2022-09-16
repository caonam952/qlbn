package com.caonam.qlbn.dto;

import com.caonam.qlbn.entities.Medicine;
import com.caonam.qlbn.entities.Prescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
