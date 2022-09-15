package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.EmployeeRepository;
import com.caonam.qlbn.dto.EmployeeDto;
import com.caonam.qlbn.entities.Employee;
import com.caonam.qlbn.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
                .map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<EmployeeDto> findById(UUID id) {
        Optional<Employee> result = employeeRepository.findById(id);

        Optional<EmployeeDto> tempEmployeeDto = result.map(employee -> modelMapper.map(employee, EmployeeDto.class));

        return tempEmployeeDto;
    }


    @Override
    public void save(EmployeeDto employeeDto) {
        Employee employee = new Employee();
        employee.setName(employeeDto.getName());
        employee.setPosition(employeeDto.getPosition());
//        employee.setPrescription(modelMapper.map(employeeDto.getPrescriptionDto(), Prescription.class));
        modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }

//    @Override
//    public void update(String name, String position, int id) {
//        employeeRepository.updateEmployee();
//    }

    @Override
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public void update(EmployeeDto employeeDto, UUID id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setName(employeeDto.getName());
        employee.setPosition(employeeDto.getPosition());
//        employee.setPrescription(modelMapper.map(employeeDto.getPrescriptionDto(), Prescription.class));
        modelMapper.map(employeeRepository.save(employee), EmployeeDto.class);
    }
}
