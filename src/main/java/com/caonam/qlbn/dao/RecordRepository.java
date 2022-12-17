package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.entities.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RecordRepository extends JpaRepository<Record, UUID> {
    @Query(value = "select e from Record e order by e.createAt DESC")
    List<Record> getAll();

    Optional<Record> getRecordByPatient_Id(UUID patientId);
}
