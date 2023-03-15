package com.caonam.qlbn.controller;


import com.caonam.qlbn.dto.PatientDto;
import com.caonam.qlbn.service.PatientService;
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
public class PatientControler {

    private final PatientService patientService;

    @Autowired
    public PatientControler(PatientService thePatientService) {
        patientService = thePatientService;
    }

    @GetMapping("/patients/list")
    public ResponseEntity<List<PatientDto>> findAllPatient() {
        return new ResponseEntity<>(patientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/patients/{patientId}")
    public ResponseEntity<PatientDto> findPatientById(@PathVariable UUID patientId) {
        Optional<PatientDto> patientDtoOptional = patientService.findById(patientId);
        return patientDtoOptional.map(patientDto -> {
            return new ResponseEntity<>(patientDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/patients")
    public ResponseEntity<?> savePatient(@RequestBody @Valid PatientDto patientDto) {
//        patientDto.setId(null);
        patientService.save(patientDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/patients/{patientId}")
    public ResponseEntity<?> updatePatient(@RequestBody @Valid PatientDto patientDto, @PathVariable UUID patientId) {
        Optional<PatientDto> patientDtoOptional = patientService.findById(patientId);
        return patientDtoOptional.map(patientDTO -> {
            patientService.update(patientDto, patientId);
            return new ResponseEntity<>(patientDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/patients/{patientId}")
    public ResponseEntity<PatientDto> deletePatient(@PathVariable UUID patientId) {
        // Lấy thử đối tượng có id đó ra xem tồn tại chưa để xóa, ko thì trả về status not found
        Optional<PatientDto> patientDtoOptional = patientService.findById(patientId);
        return patientDtoOptional.map(patientDTO -> {
            patientService.deleteById(patientId);
            return new ResponseEntity<>(patientDTO, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/patients/countPatient")
    public Integer countPatient() {
        return patientService.countPatient();
    }
}
