package com.caonam.qlbn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

//đơn thuốc
@Entity
@Table(name = "prescription")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Prescription {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

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

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @OneToMany(mappedBy = "prescription",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Medicine> medicines;
}
