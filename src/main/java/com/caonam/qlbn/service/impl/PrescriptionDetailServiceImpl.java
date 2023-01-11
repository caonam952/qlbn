package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.MedicineRepository;
import com.caonam.qlbn.dao.PrescriptionDetailRepository;
import com.caonam.qlbn.dao.PrescriptionRepository;
import com.caonam.qlbn.dto.MedicineDto;
import com.caonam.qlbn.dto.PrescriptionDetailDto;
import com.caonam.qlbn.dto.PrescriptionDto;
import com.caonam.qlbn.entities.*;
import com.caonam.qlbn.service.PrescriptionDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class PrescriptionDetailServiceImpl implements PrescriptionDetailService {

    @Autowired
    private ModelMapper modelMapper;

    private static PrescriptionDetailRepository prescriptionDetailRepository;

    private static PrescriptionRepository prescriptionRepository;

//    private static MedicineRepository medicineRepository;
    @Autowired
    public PrescriptionDetailServiceImpl(PrescriptionDetailRepository thePrescriptionDetailRepository, PrescriptionRepository thePrescriptionRepository
//            , MedicineRepository theMedicineRepository
    ) {
//        medicineRepository = theMedicineRepository;
        prescriptionDetailRepository = thePrescriptionDetailRepository;
        prescriptionRepository = thePrescriptionRepository;
    }

    @Override
    public List<PrescriptionDetailDto> findAll() {
        return prescriptionDetailRepository.getAll()
                .stream()
                .map(PrescriptionDetailDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PrescriptionDetailDto> findById(UUID id) {
        Optional<PrescriptionDetail> result = prescriptionDetailRepository.findById(id);

        Optional<PrescriptionDetailDto> temPrescriptionDetailDto = result.map(prescriptionDetail -> modelMapper.map(prescriptionDetail, PrescriptionDetailDto.class));

        return temPrescriptionDetailDto;
    }

    @Override
    public List<PrescriptionDetailDto> findAllByPrescriptionId(UUID prescriptionId) {
        return  prescriptionDetailRepository.findAllByPrescription_Id(prescriptionId)
                .stream()
                .map(PrescriptionDetailDto::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void save(PrescriptionDetailDto prescriptionDetailDto) {
        PrescriptionDetail prescriptionDetail = new PrescriptionDetail();
        setPrescriptionDetail(prescriptionDetailDto, prescriptionDetail);
        prescriptionDetail.setCreateAt(new Date());

        Optional<Prescription> prescription = prescriptionRepository.findById(prescriptionDetailDto.getPrescriptionDto().getId());
        prescription.ifPresent(prescriptionDetail::setPrescription);

        modelMapper.map(prescriptionDetailRepository.save(prescriptionDetail), PrescriptionDetailDto.class);

    }

    @Override
    public void update(PrescriptionDetailDto prescriptionDetailDto, UUID id) {
        PrescriptionDetail prescriptionDetail = prescriptionDetailRepository.findById(id).orElse(new PrescriptionDetail());

        setPrescriptionDetail(prescriptionDetailDto, prescriptionDetail);

        modelMapper.map(prescriptionDetailRepository.save(prescriptionDetail), PrescriptionDetailDto.class);

    }

    @Override
    public void deleteById(UUID id) {
        prescriptionDetailRepository.deleteById(id);
    }

    private void setPrescriptionDetail(PrescriptionDetailDto prescriptionDetailDto, PrescriptionDetail prescriptionDetail) {
        prescriptionDetail.setMedicine(prescriptionDetailDto.getMedicine());
        prescriptionDetail.setAmount(prescriptionDetailDto.getAmount());
        prescriptionDetail.setDosage(prescriptionDetailDto.getDosage());



//        if (!ObjectUtils.isEmpty(prescriptionDetailDto.getPrescriptionDto())) {
//            prescriptionRepository.findById(prescriptionDetailDto.getPrescriptionDto().getId())
//                    .map(prescription -> {
//                        prescriptionDetail.setPrescription(prescription);
//                        return prescriptionDetail;
//                    });
//        }

//        if (!ObjectUtils.isEmpty(prescriptionDetailDto.getMedicineDto())) {
//            medicineRepository.findById(prescriptionDetailDto.getMedicineDto().getId())
//                    .map(medicine -> {
//                        prescriptionDetail.setMedicine(medicine);
//                        return prescriptionDetail;
//                    });
//        }

//        prescriptionDetail.setPrescription(modelMapper.map(prescriptionDetailDto.getPrescriptionDto(), Prescription.class));
//        prescriptionDetail.setMedicine(modelMapper.map(prescriptionDetailDto.getMedicineDto(), Medicine.class));
    }

}
