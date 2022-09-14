package com.caonam.qlbn.service;

import com.caonam.qlbn.dto.EmployeeDto;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeService extends GeneralService<EmployeeDto>{
    List<EmployeeDto> findAll();

//    void update(EmployeeDto employeeDto, int id);

    void deleteById(int id);
}
