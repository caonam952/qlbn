package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.EmployeeRepository;
import com.caonam.qlbn.dao.PatientRepository;
import com.caonam.qlbn.dao.PrescriptionDetailRepository;
import com.caonam.qlbn.dao.PrescriptionRepository;
import com.caonam.qlbn.dto.PrescriptionDetailDto;
import com.caonam.qlbn.dto.PrescriptionDto;
import com.caonam.qlbn.entities.Patient;
import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.entities.PrescriptionDetail;
import com.caonam.qlbn.service.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private ModelMapper modelMapper;

    private static PrescriptionRepository prescriptionRepository;

    private static PrescriptionDetailRepository prescriptionDetailRepository;

    private static EmployeeRepository employeeRepository;

    private static PatientRepository patientRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository thePrescriptionRepository, PrescriptionDetailRepository thePrescriptionDetailRepository, EmployeeRepository theEmployeeRepository, PatientRepository thePatientRepository) {
        prescriptionRepository = thePrescriptionRepository;
        prescriptionDetailRepository = thePrescriptionDetailRepository;
        employeeRepository = theEmployeeRepository;
        patientRepository = thePatientRepository;
    }

    @Override
    public List<PrescriptionDto> findAll() {
        return prescriptionRepository.getAll()
                .stream()
                .map(PrescriptionDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrescriptionDto> findById(UUID id) {
        Optional<Prescription> result = prescriptionRepository.findById(id);

//        Optional<PrescriptionDto> temPrescriptionDto = result.map(prescription -> modelMapper.map(prescription, PrescriptionDto.class));
        Optional<PrescriptionDto> temPrescriptionDto = result.map(PrescriptionDto::toDto);

        return temPrescriptionDto;
    }

    @Override
    public List<PrescriptionDto> findAllByPatientId(UUID patientId) {
        return prescriptionRepository.findAllByPatient_Id(patientId)
                .stream()
                .map(PrescriptionDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(PrescriptionDto prescriptionDto) {
        Prescription prescription = new Prescription();

        setPrescription(prescriptionDto, prescription);
        prescription.setCreateAt(new Date());
//        if (!ObjectUtils.isEmpty(prescriptionDto.getPrescriptionDetailDtos())) {
//            prescription.setPrescriptionDetails(initPrescriptionDetail(prescriptionDto.getPrescriptionDetailDtos(), prescription));
//        }
        modelMapper.map(prescriptionRepository.save(prescription), PrescriptionDto.class);
    }

    @Override
    public void update(PrescriptionDto prescriptionDto, UUID id) {
        Prescription prescription = prescriptionRepository.findById(id).orElse(new Prescription());

        setPrescription(prescriptionDto, prescription);
//        if (!ObjectUtils.isEmpty(prescriptionDto.getPrescriptionDetailDtos())) {
//            for (PrescriptionDetailDto prescriptionDetailDto : prescriptionDto.getPrescriptionDetailDtos()) {
//                prescriptionDetailRepository.findById(prescriptionDetailDto.getId())
//                        .map(prescriptionDetail -> {
//                            prescription.setPrescriptionDetails(prescription);
//                            return prescriptionDetail;
//                        });
//            }
//        }

        modelMapper.map(prescriptionRepository.save(prescription), PrescriptionDto.class);

    }

    private void setPrescription(PrescriptionDto prescriptionDto, Prescription prescription) {
        prescription.setPrescriptionDate(prescriptionDto.getPrescriptionDate());
        prescription.setAppointmentDate(prescriptionDto.getAppointmentDate());
        prescription.setNote(prescriptionDto.getNote());

        if (!ObjectUtils.isEmpty(prescriptionDto.getEmployeeDto())) {
            employeeRepository.findById(prescriptionDto.getEmployeeDto().getId())
                    .map(employee -> {
                        prescription.setEmployee(employee);
                        return prescription;
                    });
        }

        if (!ObjectUtils.isEmpty(prescriptionDto.getPatientDto())) {
            patientRepository.findById(prescriptionDto.getPatientDto().getId())
                    .map(patient -> {
                        prescription.setPatient(patient);
                        return prescription;
                    });
        }

        if (!ObjectUtils.isEmpty(prescriptionDto.getPrescriptionDetailDtos())) {
            prescription.setPrescriptionDetails(initPrescriptionDetail(prescriptionDto.getPrescriptionDetailDtos(), prescription));
        }
//        prescription.setPatient(modelMapper.map(prescriptionDto.getPatientDto(), Patient.class));
//        prescription.setEmployee(modelMapper.map(prescriptionDto.getEmployeeDto(), Employee.class));
    }

    public List<PrescriptionDetail> initPrescriptionDetail(List<PrescriptionDetailDto> prescriptionDetailDtos, Prescription prescription) {
        if (!prescriptionDetailDtos.isEmpty() && prescription != null) {
            List<PrescriptionDetail> prescriptionDetails = new ArrayList<>();
            for (PrescriptionDetailDto prescriptionDetailDto : prescriptionDetailDtos) {
                PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
                prescriptionDetail.setAmount(prescriptionDetailDto.getAmount());
                prescriptionDetail.setDosage(prescriptionDetailDto.getDosage());
                prescriptionDetail.setPrescription(prescription);
                prescriptionDetails.add(prescriptionDetail);
            }
        }
        return null;
    }

    @Override
    public void deleteById(UUID id) {
        prescriptionRepository.deleteById(id);
    }
}
