package com.caonam.qlbn.controller;

import com.caonam.qlbn.dto.EmployeeDto;
import com.caonam.qlbn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<EmployeeDto>> findAllEmployee() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable int employeeId) {
        Optional<EmployeeDto> employeeDtoOptional = employeeService.findById(employeeId);
        return employeeDtoOptional.map(employeeDto -> {
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> saveEmployee(EmployeeDto employeeDto) {
        employeeDto.setId(0);
        employeeService.save(employeeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(EmployeeDto employeeDto, @PathVariable int employeeId) {
        Optional<EmployeeDto> employeeDtoOptional = employeeService.findById(employeeId);
        return employeeDtoOptional.map(employeeDTO -> {
            employeeService.save(employeeDto);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable int employeeId) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<EmployeeDto> employeeDtoOptional = employeeService.findById(employeeId);
        return employeeDtoOptional.map(employeeDTO -> {
            employeeService.deleteById(employeeId);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
