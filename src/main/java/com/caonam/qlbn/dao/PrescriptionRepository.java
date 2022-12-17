package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.Patient;
import com.caonam.qlbn.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
    @Query(value = "select e from Prescription e order by e.createAt DESC")
    List<Prescription> getAll();

    List<Prescription> findAllByPatient_Id(UUID patientId);
}
