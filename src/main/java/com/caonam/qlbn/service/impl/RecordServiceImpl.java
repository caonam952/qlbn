package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.RecordRepository;
import com.caonam.qlbn.dto.RecordDto;
import com.caonam.qlbn.entities.Patient;
import com.caonam.qlbn.entities.Record;
import com.caonam.qlbn.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private ModelMapper modelMapper;

    private static RecordRepository recordRepository;

    public RecordServiceImpl(RecordRepository theRecordRepository) {
        recordRepository = theRecordRepository;
    }

    @Override
    public List<RecordDto> findAll() {
        return recordRepository.findAll()
                .stream()
                .map(record -> modelMapper.map(record, RecordDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RecordDto> findById(UUID id) {
        Optional<Record> result = recordRepository.findById(id);

        Optional<RecordDto> tempRecordDto = result.map(record -> modelMapper.map(record, RecordDto.class));

        return tempRecordDto;
    }

    @Override
    public void save(RecordDto recordDto) {
        Record record = new Record();
        setRecord(recordDto,record);
        modelMapper.map(recordRepository.save(record), RecordDto.class);
    }

    @Override
    public void update(RecordDto recordDto, UUID id) {
        Record record = recordRepository.findById(id).orElse(null);
        setRecord(recordDto, record);
        modelMapper.map(recordRepository.save(record), RecordDto.class);
    }


    private void setRecord(RecordDto recordDto, Record record) {
        record.setMedicalHistory(recordDto.getMedicalHistory());
        record.setProductInUse(record.getProductInUse());
        record.setDiagnose(recordDto.getDiagnose());
        record.setResult(recordDto.getResult());
        record.setRegimen(recordDto.getRegimen());
        record.setPreImage(recordDto.getPreImage());
        record.setAfterImage(record.getAfterImage());
        record.setPatient(modelMapper.map(recordDto.getPatientDto(), Patient.class));
    }

    @Override
    public void deleteById(UUID id) {
        recordRepository.deleteById(id);
    }
}
