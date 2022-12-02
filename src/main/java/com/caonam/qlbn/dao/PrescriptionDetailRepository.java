package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.Prescription;
import com.caonam.qlbn.entities.PrescriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetail, UUID> {
    @Query(value = "select e from PrescriptionDetail e order by e.createAt DESC")
    List<PrescriptionDetail> getAll();
}
