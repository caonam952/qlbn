package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.PrescriptionDetailRepository;
import com.caonam.qlbn.dto.MedicineDto;
import com.caonam.qlbn.dto.PrescriptionDetailDto;
import com.caonam.qlbn.dto.PrescriptionDto;
import com.caonam.qlbn.entities.*;
import com.caonam.qlbn.service.PrescriptionDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class PrescriptionDetailServiceImpl implements PrescriptionDetailService {

    @Autowired
    private ModelMapper modelMapper;

    private static PrescriptionDetailRepository prescriptionDetailRepository;

    public PrescriptionDetailServiceImpl(PrescriptionDetailRepository thePrescriptionDetailRepository) {
        prescriptionDetailRepository = thePrescriptionDetailRepository;
    }

    @Override
    public List<PrescriptionDetailDto> findAll() {
        return prescriptionDetailRepository.findAll()
                .stream()
                .map(prescriptionDetail -> modelMapper.map(prescriptionDetail, PrescriptionDetailDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrescriptionDetailDto> findById(UUID id) {
        Optional<PrescriptionDetail> result = prescriptionDetailRepository.findById(id);

        Optional<PrescriptionDetailDto> temPrescriptionDetailDto = result.map(prescriptionDetail -> modelMapper.map(prescriptionDetail, PrescriptionDetailDto.class));

        return temPrescriptionDetailDto;
    }

    @Override
    public void save(PrescriptionDetailDto prescriptionDetailDto) {
        PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
        setPrescriptionDetail(prescriptionDetailDto, prescriptionDetail);
        modelMapper.map(prescriptionDetailRepository.save(prescriptionDetail), PrescriptionDetailDto.class);

    }

    @Override
    public void update(PrescriptionDetailDto prescriptionDetailDto, UUID id) {
        PrescriptionDetail prescriptionDetail = prescriptionDetailRepository.findById(id).orElse(null);

        setPrescriptionDetail(prescriptionDetailDto, prescriptionDetail);

        modelMapper.map(prescriptionDetailRepository.save(prescriptionDetail), PrescriptionDto.class);

    }

    @Override
    public void deleteById(UUID id) {
        prescriptionDetailRepository.deleteById(id);
    }

    private void setPrescriptionDetail(PrescriptionDetailDto prescriptionDetailDto, PrescriptionDetail prescriptionDetail) {
        prescriptionDetail.setAmount(prescriptionDetailDto.getAmount());
        prescriptionDetail.setDosage(prescriptionDetailDto.getDosage());
        prescriptionDetail.setPrescription(modelMapper.map(prescriptionDetailDto.getPrescriptionDto(), Prescription.class));
        prescriptionDetail.setMedicine(modelMapper.map(prescriptionDetailDto.getMedicineDto(), Medicine.class));
    }

}
