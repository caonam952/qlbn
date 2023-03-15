package com.caonam.qlbn.controller;

import com.caonam.qlbn.dto.PrescriptionDto;
import com.caonam.qlbn.entities.ConvertStatistic;
import com.caonam.qlbn.service.PrescriptionService;
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
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    @Autowired
    public PrescriptionController(PrescriptionService thePrescriptionService) {
        prescriptionService = thePrescriptionService;
    }

    @GetMapping("/prescriptions/list")
    public ResponseEntity<List<PrescriptionDto>> findAllPrescription() {
        return new ResponseEntity<>(prescriptionService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<PrescriptionDto> findPrescriptionById(@PathVariable UUID prescriptionId) {
        Optional<PrescriptionDto> prescriptionDtoOptional = prescriptionService.findById(prescriptionId);
        return prescriptionDtoOptional.map(prescriptionDto -> {
            return new ResponseEntity<>(prescriptionDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/prescriptions/patientId={patientId}")
    public ResponseEntity<List<PrescriptionDto>> findAllPrescriptionByPatientId(@PathVariable UUID patientId) {
//        Optional<PrescriptionDto> prescriptionDtoOptional = prescriptionService.findAllByPatientId(patientId);
//        return prescriptionDtoOptional.map(prescriptionDto -> {
//            return new ResponseEntity<>(prescriptionDto, HttpStatus.OK);
//        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return new ResponseEntity<>(prescriptionService.findAllByPatientId(patientId), HttpStatus.OK);
    }

    @PostMapping("/prescriptions")
    public ResponseEntity<?> savePrescription(@RequestBody @Valid PrescriptionDto prescriptionDto) {
        prescriptionService.save(prescriptionDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<?> updatePrescription(@RequestBody @Valid PrescriptionDto prescriptionDto, @PathVariable UUID prescriptionId) {
        Optional<PrescriptionDto> prescriptionDtoOptional = prescriptionService.findById(prescriptionId);
        return prescriptionDtoOptional.map(prescriptionDTO -> {
            prescriptionService.update(prescriptionDto, prescriptionId);
            return new ResponseEntity<>(prescriptionDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/prescriptions/{prescriptionId}")
    public ResponseEntity<PrescriptionDto> deletePrescription(@PathVariable UUID prescriptionId) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<PrescriptionDto> prescriptionDtoOptional = prescriptionService.findById(prescriptionId);
        return prescriptionDtoOptional.map(prescriptionDTO -> {
            prescriptionService.deleteById(prescriptionId);
            return new ResponseEntity<>(prescriptionDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/prescriptions/countByAttendingDoctor")
    public List<ConvertStatistic> countPrescriptionByAttendingDoctor(){
        return prescriptionService.countPrescriptionByAttendingDoctor();
    }

    @GetMapping("/prescriptions/countByPatient")
    public List<ConvertStatistic> countPrescriptionByPatient(){
        return prescriptionService.countPrescriptionByPatient();
    }

    @GetMapping("/prescriptions/countByMonth")
    public List<ConvertStatistic> countPrescriptionByMonth(){
        return prescriptionService.countPrescriptionByMonth();
    }

    @GetMapping("/prescriptions/countPrescription")
    public Integer countPrescription() {
        return prescriptionService.countPrescription();
    }


}
