package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, UUID> {
    @Query(value = "select e from Medicine e order by e.createAt DESC")
    List<Medicine> getAll();
}
