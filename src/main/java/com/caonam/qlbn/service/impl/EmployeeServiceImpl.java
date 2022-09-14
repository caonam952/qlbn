package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.EmployeeRepository;
import com.caonam.qlbn.dto.EmployeeDto;
import com.caonam.qlbn.entities.Employee;
import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private ModelMapper modelMapper;

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll()
                .stream()
                .map(EmployeeDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDto> findById(Integer id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Optional<EmployeeDto> tempEmployeeDto = result.map(result1 -> modelMapper.map(result1, EmployeeDto.class));

        return tempEmployeeDto;
    }


    @Override
    public EmployeeDto save(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setPosition(employeeDto.getPosition());
        employee.setPrescription(modelMapper.map(employeeDto.getPrescriptionDto(), Prescription.class));
        return modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

//    @Override
//    public void update(String name, String position, int id) {
//        employeeRepository.updateEmployee();
//    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }


}
