package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.PrescriptionDetailRepository;
import com.caonam.qlbn.dao.PrescriptionRepository;
import com.caonam.qlbn.dto.PrescriptionDetailDto;
import com.caonam.qlbn.dto.PrescriptionDto;
import com.caonam.qlbn.entities.Employee;
import com.caonam.qlbn.entities.Patient;
import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.entities.PrescriptionDetail;
import com.caonam.qlbn.service.PrescriptionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private ModelMapper modelMapper;

    private static PrescriptionRepository prescriptionRepository;

    private static PrescriptionDetailRepository prescriptionDetailRepository;

    @Autowired
    public PrescriptionServiceImpl(PrescriptionRepository thePrescriptionRepository, PrescriptionDetailRepository thePrescriptionDetailRepository) {
        prescriptionRepository = thePrescriptionRepository;
        prescriptionDetailRepository = thePrescriptionDetailRepository;
    }

    @Override
    public List<PrescriptionDto> findAll() {
        return prescriptionRepository.findAll()
                .stream()
                .map(prescription -> modelMapper.map(prescription, PrescriptionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrescriptionDto> findById(UUID id) {
        Optional<Prescription> result = prescriptionRepository.findById(id);

        Optional<PrescriptionDto> temPrescriptionDto = result.map(prescription -> modelMapper.map(prescription, PrescriptionDto.class));

        return temPrescriptionDto;
    }

    @Override
    public void save(PrescriptionDto prescriptionDto) {
        Prescription prescription = new Prescription();

        setPrescription(prescriptionDto, prescription);
        prescription.setPrescriptionDetails(initPrescriptionDetail(prescriptionDto.getPrescriptionDetailDtos(), prescription));

        modelMapper.map(prescriptionRepository.save(prescription), PrescriptionDto.class);
    }

    @Override
    public void update(PrescriptionDto prescriptionDto, UUID id) {
        Prescription prescription = prescriptionRepository.findById(id).orElse(null);

        setPrescription(prescriptionDto, prescription);
        if (!prescriptionDto.getPrescriptionDetailDtos().isEmpty()) {
            for (PrescriptionDetailDto prescriptionDetailDto : prescriptionDto.getPrescriptionDetailDtos()) {
                prescriptionDetailRepository.findById(prescriptionDetailDto.getId())
                        .map(prescriptionDetail -> {
                            prescriptionDetail.setAmount(prescriptionDetailDto.getAmount());
                            prescriptionDetail.setDosage(prescriptionDetailDto.getDosage());
                            prescriptionDetail.setPrescription(prescription);
                            return prescriptionDetail;
                        });
            }
        }

        modelMapper.map(prescriptionRepository.save(prescription), PrescriptionDto.class);

    }

    private void setPrescription(PrescriptionDto prescriptionDto, Prescription prescription) {
        prescription.setPrescriptionDate(prescriptionDto.getPrescriptionDate());
        prescription.setAppointmentDate(prescriptionDto.getAppointmentDate());
        prescription.setPatient(modelMapper.map(prescriptionDto.getPatientDto().getId(), Patient.class));
        prescription.setEmployee(modelMapper.map(prescriptionDto.getEmployeeDto().getId(), Employee.class));
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
