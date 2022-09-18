package com.caonam.qlbn.service.impl;

import com.caonam.qlbn.dao.PatientRepository;
import com.caonam.qlbn.dao.RecordRepository;
import com.caonam.qlbn.dto.RecordDto;
import com.caonam.qlbn.entities.Patient;
import com.caonam.qlbn.entities.Record;
import com.caonam.qlbn.service.RecordService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private ModelMapper modelMapper;

    private static PatientRepository patientRepository;

    private static RecordRepository recordRepository;

    @Autowired
    public RecordServiceImpl(RecordRepository theRecordRepository,PatientRepository thePatientRepository) {
        recordRepository = theRecordRepository;
        patientRepository = thePatientRepository;
    }

    @Override
    public List<RecordDto> findAll() {
        return recordRepository.findAll()
                .stream()
                .map(RecordDto::toDto)
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
        setRecord(recordDto, record);

//        Optional<Patient> patient = patientRepository.findById(recordDto.getPatientDto().getId());
//        patient.ifPresent(record::setPatient);

        if (!ObjectUtils.isEmpty(recordDto.getPatientDto())) {
            patientRepository.findById(recordDto.getPatientDto().getId())
                    .map(patient -> {
                                record.setPatient(patient);
                                return record;
                            });
        }

//        record.setPatient(modelMapper.map(recordDto.getPatientDto().getId(), Patient.class));


        modelMapper.map(recordRepository.save(record), RecordDto.class);
    }

    @Override
    public void update(RecordDto recordDto, UUID id) {
        Record record = recordRepository.findById(id).orElse(new Record());
        setRecord(recordDto, record);

        if (!ObjectUtils.isEmpty(recordDto.getPatientDto())) {
            patientRepository.findById(recordDto.getPatientDto().getId())
                    .map(patient -> {
                        record.setPatient(patient);
                        return record;
                    });
        }
        modelMapper.map(recordRepository.save(record), RecordDto.class);
    }


    private void setRecord(RecordDto recordDto, Record record) {
        record.setMedicalHistory(recordDto.getMedicalHistory());
        record.setProductInUse(recordDto.getProductInUse());
        record.setDiagnose(recordDto.getDiagnose());
        record.setResult(recordDto.getResult());
        record.setRegimen(recordDto.getRegimen());
        record.setPreImage(recordDto.getPreImage());
        record.setAfterImage(recordDto.getAfterImage());
    }

    @Override
    public void deleteById(UUID id) {
        recordRepository.deleteById(id);
    }
}
