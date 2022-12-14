package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.PatientRepository;
import com.caonam.qlbn.dto.MedicineDto;
import com.caonam.qlbn.dto.PatientDto;
import com.caonam.qlbn.dto.PrescriptionDto;
import com.caonam.qlbn.entities.Medicine;
import com.caonam.qlbn.entities.Patient;
import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.entities.Record;
import com.caonam.qlbn.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
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
        return patientRepository.getAll()
                .stream()
//                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .map(PatientDto::toDto)
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
        patient.setCreateAt(new Date());
        if (!ObjectUtils.isEmpty(patientDto.getRecordDto())) {
            patient.setRecord(modelMapper.map(patientDto.getRecordDto(), Record.class));
        }
        if (!ObjectUtils.isEmpty(patientDto.getPrescriptionDtos())) {
            patient.setPrescriptions(initPrescription(patientDto.getPrescriptionDtos(), patient));
        }
        modelMapper.map(patientRepository.save(patient), PatientDto.class);
    }

    @Override
    public void update(PatientDto patientDto, UUID id) {
        Patient patient = patientRepository.findById(id).orElse(null);

        setPatient(patientDto, patient);

        if (!ObjectUtils.isEmpty(patientDto.getRecordDto())) {
            patient.setRecord(modelMapper.map(patientDto.getRecordDto(), Record.class));
        }
        if (!ObjectUtils.isEmpty(patientDto.getPrescriptionDtos())) {
            patient.setPrescriptions(initPrescription(patientDto.getPrescriptionDtos(), patient));
        }
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

    public List<Prescription> initPrescription(List<PrescriptionDto> prescriptionDtos, Patient patient) {
        if (!prescriptionDtos.isEmpty() && patient != null) {
            List<Prescription> prescriptions = new ArrayList<>();
            for (PrescriptionDto prescriptionDto : prescriptionDtos) {
                Prescription prescription = new Prescription();
                prescription.setPrescriptionDate(prescriptionDto.getPrescriptionDate());
                prescription.setAppointmentDate(prescriptionDto.getAppointmentDate());
                prescription.setPatient(patient);
                prescriptions.add(prescription);
            }
        }
        return null;
    }

//    public List<Medicine> initMedicine(List<MedicineDto> medicineDtos) {
//        if (!medicineDtos.isEmpty()) {
//            List<Medicine> medicines = new ArrayList<>();
//            for (MedicineDto medicineDto : medicineDtos) {
//                Medicine medicine = new Medicine();
//                MedicineServiceImpl.setMedicine(medicineDto, medicine);
//                medicines.add(medicine);
//            }
//        }
//        return null;
//
//    }

//    private void setMedicine(MedicineDto medicineDto, Medicine medicine) {
//        medicine.setName(medicineDto.getName());
//        medicine.setOrigin(medicineDto.getOrigin());
//        medicine.setUni(medicineDto.getUni());
//        medicine.setAmount(medicineDto.getAmount());
//        medicine.setExpDate(medicineDto.getExpDate());
//        medicine.setImportPrice(medicineDto.getImportPrice());
//        medicine.setPrice(medicineDto.getPrice());
//        medicine.setManual(medicineDto.getManual());
//        medicine.setNote(medicineDto.getNote());
//    }

    @Override
    public void deleteById(UUID id) {
        patientRepository.deleteById(id);
    }
}
