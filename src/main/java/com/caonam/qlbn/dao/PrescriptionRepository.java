package com.caonam.qlbn.dao;

import com.caonam.qlbn.entities.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, UUID> {
    @Query(value = "select e from Prescription e order by e.createAt DESC")
    List<Prescription> getAll();

    @Query(value = "select e from Prescription e where e.patient.id = :patientId order by e.createAt DESC")
    List<Prescription> findAllByPatient_Id(UUID patientId);

    @Query(value = "select e.attendingDoctor as name, count(e.id) as value from Prescription e group by e.attendingDoctor")
    List<Object[]> countPrescriptionByAttendingDoctor();

    @Query(value = "select count(e.id) from Prescription e")
    Integer countPrescription();

    @Query(value = "select e.patient.name as name, count(e.id) as value from Prescription e group by e.patient.name")
    List<Object[]> countPrescriptionByPatient();

    @Query(value = "SELECT CONCAT(YEAR(e.prescriptionDate), '-', MONTH(e.prescriptionDate)) AS name, COUNT(e.id) as value FROM Prescription e GROUP BY YEAR(e.prescriptionDate), month(e.prescriptionDate) ORDER BY YEAR(e.prescriptionDate) ASC, MONTH(e.prescriptionDate) ASC")
    List<Object[]> countPrescriptionByMonth();
}
