package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.Medicine;
import com.caonam.qlbn.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {
    @Query(value = "select e from Patient e order by e.createAt DESC")
    List<Patient> getAll();

    @Query(value = "select count(e.id) from Patient e ")
    Integer countPatient();
}
