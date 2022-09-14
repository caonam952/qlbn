package com.caonam.qlbn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "medicine")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Medicine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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
