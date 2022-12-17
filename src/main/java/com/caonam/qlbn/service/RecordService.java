package com.caonam.qlbn.service;

import com.caonam.qlbn.dto.RecordDto;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;


public interface RecordService extends GeneralService<RecordDto> {
    Optional<RecordDto> getRecordByPatientId(UUID patientId);
}
