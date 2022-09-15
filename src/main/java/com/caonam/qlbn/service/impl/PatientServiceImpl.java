package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.PatientRepository;
import com.caonam.qlbn.dto.PatientDto;
import com.caonam.qlbn.entities.Patient;
import com.caonam.qlbn.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private ModelMapper modelMapper;

    private static PatientRepository patientRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository thePatientRepository) {
        patientRepository = thePatientRepository;
    }

    @Override
    public List<PatientDto> findAll() {
        return patientRepository.findAll()
                .stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PatientDto> findById(UUID id) {
        Optional<Patient> result = patientRepository.findById(id);

        Optional<PatientDto> tempPatientDto = result.map(patient -> modelMapper.map(patient, PatientDto.class));

        return tempPatientDto;
    }

    @Override
    public void save(PatientDto patientDto) {
        Patient patient = new Patient();
        setPatient(patientDto, patient);
        modelMapper.map(patientRepository.save(patient), PatientDto.class);

    }

    @Override
    public void update(PatientDto patientDto, UUID id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        setPatient(patientDto, patient);
        modelMapper.map(patientRepository.save(patient), PatientDto.class);


    }

    private void setPatient(PatientDto patientDto, Patient patient) {
        patient.setName(patientDto.getName());
        patient.setBirth(patientDto.getBirth());
        patient.setSex(patientDto.getSex());
        patient.setAddress(patientDto.getAddress());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setNote(patientDto.getNote());
    }


    @Override
    public void deleteById(UUID id) {
        patientRepository.deleteById(id);
    }
}
