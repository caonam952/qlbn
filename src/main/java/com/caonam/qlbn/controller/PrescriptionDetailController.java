package com.caonam.qlbn.controller;

import com.caonam.qlbn.dto.PrescriptionDetailDto;
import com.caonam.qlbn.service.PrescriptionDetailService;
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
public class PrescriptionDetailController {

    private final PrescriptionDetailService prescriptionDetailService;

    @Autowired
    public PrescriptionDetailController(PrescriptionDetailService thePrescriptionDetailService) {
        prescriptionDetailService = thePrescriptionDetailService;
    }

    @GetMapping("/prescription_details/list")
    public ResponseEntity<List<PrescriptionDetailDto>> findAllPrescriptionDetail() {
        return new ResponseEntity<>(prescriptionDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/prescription_details/{prescriptionDetailId}")
    public ResponseEntity<PrescriptionDetailDto> findPrescriptionDetailById(@PathVariable UUID prescriptionDetailId) {
        Optional<PrescriptionDetailDto> prescriptionDetailDtoOptional = prescriptionDetailService.findById(prescriptionDetailId);
        return prescriptionDetailDtoOptional.map(prescriptionDetailDto -> {
            return new ResponseEntity<>(prescriptionDetailDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/prescription_details/prescriptionId={prescriptionId}")
    public ResponseEntity<List<PrescriptionDetailDto>> findAllPrescriptionDetailsByPrescriptionId(@PathVariable UUID prescriptionId) {
        return new ResponseEntity<>(prescriptionDetailService.findAllByPrescriptionId(prescriptionId), HttpStatus.OK);
    }

    @PostMapping("/prescription_details")
    public ResponseEntity<?> savePrescriptionDetail(@RequestBody @Valid PrescriptionDetailDto prescriptionDetailDto) {
        prescriptionDetailService.save(prescriptionDetailDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/prescription_details/{prescriptionDetailId}")
    public ResponseEntity<?> updatePrescriptionDetail(@RequestBody @Valid PrescriptionDetailDto prescriptionDetailDto, @PathVariable UUID prescriptionDetailId) {
        Optional<PrescriptionDetailDto> prescriptionDetailDtoOptional = prescriptionDetailService.findById(prescriptionDetailId);
        return prescriptionDetailDtoOptional.map(prescriptionDetailDTO -> {
            prescriptionDetailService.update(prescriptionDetailDto, prescriptionDetailId);
            return new ResponseEntity<>(prescriptionDetailDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/prescription_details/{prescriptionDetailId}")
    public ResponseEntity<PrescriptionDetailDto> deletePrescriptionDetail(@PathVariable UUID prescriptionDetailId) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<PrescriptionDetailDto> prescriptionDetailDtoOptional = prescriptionDetailService.findById(prescriptionDetailId);
        return prescriptionDetailDtoOptional.map(prescriptionDetailDTO -> {
            prescriptionDetailService.deleteById(prescriptionDetailId);
            return new ResponseEntity<>(prescriptionDetailDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
