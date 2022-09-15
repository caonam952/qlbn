package com.caonam.qlbn.service;

import com.caonam.qlbn.dto.EmployeeDto;

import java.util.UUID;

public interface EmployeeService extends GeneralService<EmployeeDto> {
//    List<EmployeeDto> findAll();

    void update(EmployeeDto employeeDto, UUID id);

//    void deleteById(int id);


}
