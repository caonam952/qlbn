package com.caonam.qlbn.service;

import com.caonam.qlbn.dto.PatientDto;

public interface PatientService extends GeneralService<PatientDto> {
//    void deleteById(int id);

    Integer countPatient();
}
