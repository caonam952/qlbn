package com.caonam.qlbn.service;

import com.caonam.qlbn.dto.PrescriptionDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PrescriptionService extends GeneralService<PrescriptionDto> {

    List<PrescriptionDto> findAllByPatientId(UUID patientId);
}
