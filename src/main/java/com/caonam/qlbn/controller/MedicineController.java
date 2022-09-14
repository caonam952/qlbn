package com.caonam.qlbn.controller;

import com.caonam.qlbn.dto.EmployeeDto;
import com.caonam.qlbn.dto.MedicineDto;
import com.caonam.qlbn.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService theMedicineService) {
        medicineService = theMedicineService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MedicineDto>> findAllMedicine() {
        return new ResponseEntity<>(medicineService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineDto> findMedicineById(@PathVariable int medicineId) {
        Optional<MedicineDto> medicineDtoOptional = medicineService.findById(medicineId);
        return medicineDtoOptional.map(medicineDto -> {
            return new ResponseEntity<>(medicineDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> saveMedicine(MedicineDto medicineDto) {
        medicineDto.setId(0);
        medicineService.save(medicineDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{medicineId}")
    public ResponseEntity<?> updateMedicine(MedicineDto medicineDto, @PathVariable int medicineId) {
        Optional<MedicineDto> medicineDtoOptional = medicineService.findById(medicineId);
        return medicineDtoOptional.map(medicineDTO -> {
            medicineService.save(medicineDto);
            return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<MedicineDto> deleteMedicine(@PathVariable int employeeId) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<MedicineDto> medicineDtoOptional = medicineService.findById(employeeId);
        return medicineDtoOptional.map(medicineDTO -> {
            medicineService.deleteById(employeeId);
            return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
