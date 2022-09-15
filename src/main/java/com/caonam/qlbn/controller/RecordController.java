package com.caonam.qlbn.controller;

import com.caonam.qlbn.dto.RecordDto;
import com.caonam.qlbn.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class RecordController {

    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService theRecordService) {
        recordService = theRecordService;
    }

    @GetMapping("/records/list")
    public ResponseEntity<List<RecordDto>> findAllRecord() {
        return new ResponseEntity<>(recordService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/records/{recordId}")
    private ResponseEntity<RecordDto> findRecordById(@PathVariable UUID recordId) {
        Optional<RecordDto> recordDtoOptional = recordService.findById(recordId);
        return recordDtoOptional.map(recordDto -> {
            return new ResponseEntity<>(recordDto, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
