package com.caonam.qlbn.service;

import com.caonam.qlbn.dto.EmployeeDto;
import com.caonam.qlbn.dto.MedicineDto;

import java.util.List;

public interface MedicineService extends GeneralService<MedicineDto> {
    List<MedicineDto> findAll();

    void deleteById(int id);
}
