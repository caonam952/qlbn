package com.caonam.qlbn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

//đơn thuốc
@Entity
@Table(name = "prescription")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //ngày khám
    @Column(name = "prescription_date")
    private LocalDate prescriptionDate;

    //ngày tái khám
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    //số lượng thuốc
    @Column(name = "amount")
    private int amount;

    //liều dùng
    @Column(name = "dosage")
    private String dosage;

    @OneToOne(mappedBy = "prescription",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @OneToOne(mappedBy = "prescription",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "prescription",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Medicine> medicines;
}
