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

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Patient {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "sex")
    private String sex;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "note")
    private String note;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "record_id")
    private Record record;

    @OneToMany(mappedBy = "patient",
            cascade = CascadeType.ALL)
    @JoinColumn(name = "prescription_id")
    private List<Prescription> prescriptions;


}
