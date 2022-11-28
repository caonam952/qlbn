package com.caonam.qlbn.controller;

import com.caonam.qlbn.dto.MedicineDto;
import com.caonam.qlbn.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api")
public class MedicineController {

    private final MedicineService medicineService;

    @Autowired
    public MedicineController(MedicineService theMedicineService) {
        this.medicineService = theMedicineService;
    }

    @GetMapping("/medicines/list")
    public ResponseEntity<List<MedicineDto>> findAllMedicine() {
        return new ResponseEntity<>(medicineService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/medicines/{medicineId}")
    public ResponseEntity<MedicineDto> findMedicineById(@PathVariable UUID medicineId) {
        Optional<MedicineDto> medicineDtoOptional = medicineService.findById(medicineId);
        return medicineDtoOptional.map(medicineDto -> {
            return new ResponseEntity<>(medicineDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/medicines")
    public ResponseEntity<?> saveMedicine(@RequestBody @Valid MedicineDto medicineDto) {
//        medicineDto.setId(null);
        medicineService.save(medicineDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/medicines/{medicineId}")
    public ResponseEntity<?> updateMedicine(@RequestBody @Valid MedicineDto medicineDto, @PathVariable UUID medicineId) {
        Optional<MedicineDto> medicineDtoOptional = medicineService.findById(medicineId);
        return medicineDtoOptional.map(medicineDTO -> {
            medicineService.update(medicineDto, medicineId);
            return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/medicines/{medicinesId}")
    public ResponseEntity<MedicineDto> deleteMedicine(@PathVariable UUID medicinesId) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<MedicineDto> medicineDtoOptional = medicineService.findById(medicinesId);
        return medicineDtoOptional.map(medicineDTO -> {
            medicineService.deleteById(medicinesId);
            return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
