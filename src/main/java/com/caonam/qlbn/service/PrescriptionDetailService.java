package com.caonam.qlbn.service;

import com.caonam.qlbn.dto.PrescriptionDetailDto;

import java.util.List;
import java.util.UUID;

public interface PrescriptionDetailService extends GeneralService<PrescriptionDetailDto> {

    List<PrescriptionDetailDto> findAllByPrescriptionId(UUID prescriptionId);
}
