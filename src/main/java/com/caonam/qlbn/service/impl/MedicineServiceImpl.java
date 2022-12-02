package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.MedicineRepository;
import com.caonam.qlbn.dao.PrescriptionRepository;
import com.caonam.qlbn.dto.MedicineDto;
import com.caonam.qlbn.entities.Medicine;
import com.caonam.qlbn.service.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private ModelMapper modelMapper;

    private static MedicineRepository medicineRepository;

    private static PrescriptionRepository prescriptionRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository theMedicineRepository, PrescriptionRepository thePrescriptionRepository) {
        medicineRepository = theMedicineRepository;
        prescriptionRepository = thePrescriptionRepository;
    }

    @Override
    public List<MedicineDto> findAll() {
        return medicineRepository.getAll()
                .stream()
                .map(medicine -> modelMapper.map(medicine, MedicineDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineDto> findById(UUID id) {
        Optional<Medicine> result = medicineRepository.findById(id);

        Optional<MedicineDto> tempMedicineDto = result.map(medicine -> modelMapper.map(medicine, MedicineDto.class));

        return tempMedicineDto;
    }

    @Override
    public void save(MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        setMedicine(medicineDto, medicine);
        medicine.setCreateAt(new Date());
        modelMapper.map(medicineRepository.save(medicine), MedicineDto.class);
    }

    @Override
    public void update(MedicineDto medicineDto, UUID id) {
        Medicine medicine = medicineRepository.findById(id).orElse(null);
        setMedicine(medicineDto, medicine);
        modelMapper.map(medicineRepository.save(medicine), MedicineDto.class);
    }

    static void setMedicine(MedicineDto medicineDto, Medicine medicine) {
        medicine.setName(medicineDto.getName());
        medicine.setOrigin(medicineDto.getOrigin());
        medicine.setUni(medicineDto.getUni());
        medicine.setAmount(medicineDto.getAmount());
        medicine.setImportDate(medicineDto.getImportDate());
        medicine.setExpDate(medicineDto.getExpDate());
        medicine.setImportPrice(medicineDto.getImportPrice());
        medicine.setPrice(medicineDto.getPrice());
        medicine.setManual(medicineDto.getManual());
        medicine.setNote(medicineDto.getNote());
    }


    @Override
    public void deleteById(UUID id) {
        medicineRepository.deleteById(id);
    }
}
