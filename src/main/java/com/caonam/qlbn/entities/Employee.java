package com.caonam.qlbn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Employee {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();

    @Column(name = "name")
    private String name;

    //chức vụ
    @Column(name = "position")
    private String position;

    @OneToMany(mappedBy = "employee",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "prescription_id")
    private List<Prescription> prescription;
}
