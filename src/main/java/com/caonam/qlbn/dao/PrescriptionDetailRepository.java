package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.PrescriptionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrescriptionDetailRepository extends JpaRepository<PrescriptionDetail, UUID> {
}
