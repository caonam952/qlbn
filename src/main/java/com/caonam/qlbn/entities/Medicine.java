package com.caonam.qlbn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "medicine")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Medicine {
    @Id
    @Column(name = "id")
    @Type(type = "uuid-char")
    private UUID id = UUID.randomUUID();


    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    @Column(name = "uni")
    private String uni;

    @Column(name = "amount")
    private int amount;

    @Column(name = "exp_date")
    private LocalDate expDate;

    @Column(name = "import_price")
    private Double importPrice;

    @Column(name = "price")
    private Double price;

    @Column(name = "manual")
    private String manual;

    @Column(name = "note")
    private String note;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
}
