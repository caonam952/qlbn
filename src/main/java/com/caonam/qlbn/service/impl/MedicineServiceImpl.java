package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.MedicineRepository;
import com.caonam.qlbn.dto.MedicineDto;
import com.caonam.qlbn.entities.Medicine;
import com.caonam.qlbn.service.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private ModelMapper modelMapper;

    private static MedicineRepository medicineRepository;

    @Autowired
    public MedicineServiceImpl(MedicineRepository theMedicineRepository) {
        medicineRepository = theMedicineRepository;
    }

    @Override
    public List<MedicineDto> findAll() {
        return medicineRepository.findAll()
                .stream()
                .map(medicine -> modelMapper.map(medicine, MedicineDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MedicineDto> findById(UUID id) {
        Optional<Medicine> result = medicineRepository.findById(id);

        Optional<MedicineDto> tempMedicineDto = result.map(result1 -> modelMapper.map(result1, MedicineDto.class));

        return tempMedicineDto;
    }

    @Override
    public void save(MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        medicine.setName(medicineDto.getName());
        medicine.setOrigin(medicineDto.getOrigin());
        medicine.setUni(medicineDto.getUni());
        medicine.setAmount(medicineDto.getAmount());
        medicine.setExpDate(medicineDto.getExpDate());
        medicine.setImportPrice(medicineDto.getImportPrice());
        medicine.setPrice(medicineDto.getPrice());
        medicine.setManual(medicineDto.getManual());
        medicine.setNote(medicineDto.getNote());
        modelMapper.map(medicineRepository.save(medicine), MedicineDto.class);
    }
    
    @Override
    public void deleteById(UUID id) {
        medicineRepository.deleteById(id);
    }
}
