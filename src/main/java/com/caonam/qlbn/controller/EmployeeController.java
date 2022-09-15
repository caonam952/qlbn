package com.caonam.qlbn.controller;

import com.caonam.qlbn.dto.EmployeeDto;
import com.caonam.qlbn.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    @GetMapping("/employees/list")
    public ResponseEntity<List<EmployeeDto>> findAllEmployee() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> findEmployeeById(@PathVariable UUID employeeId) {
        Optional<EmployeeDto> employeeDtoOptional = employeeService.findById(employeeId);
        return employeeDtoOptional.map(employeeDto -> {
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/employees")
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid EmployeeDto employeeDto) {
        employeeDto.setId(null);
        employeeService.save(employeeDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/employees/{employeeId}")
    public ResponseEntity<?> updateEmployee(@RequestBody @Valid EmployeeDto employeeDto, @PathVariable UUID employeeId) {
        Optional<EmployeeDto> employeeDtoOptional = employeeService.findById(employeeId);
        return employeeDtoOptional.map(employeeDTO -> {
            employeeService.update(employeeDto, employeeId);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/employees/{employeeId}")
    public ResponseEntity<EmployeeDto> deleteEmployee(@PathVariable UUID employeeId) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<EmployeeDto> employeeDtoOptional = employeeService.findById(employeeId);
        return employeeDtoOptional.map(employeeDTO -> {
            employeeService.deleteById(employeeId);
            return new ResponseEntity<>(employeeDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
