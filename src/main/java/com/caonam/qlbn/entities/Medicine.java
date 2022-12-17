package com.caonam.qlbn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
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
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    @Column(name = "uni")
    private String uni;

    @Column(name = "amount")
    private int amount;

    @Column(name = "import_date")
    private LocalDate importDate;

    @Column(name = "exp_date")
    private LocalDate expDate;

    @Column(name = "import_price")
    private Double importPrice;

    @Column(name = "price")
    private Double price;

    @Column(name = "manual")
    private String manual;

    @Column(name = "note", length = 65555)
    private String note;

    @Column(name = "create_at")
    private Date createAt;

//    @ManyToOne(fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
//    @JoinColumn(name = "prescription_id")
//    private Prescription prescription;

//    @ManyToOne(fetch = FetchType.LAZY,
//            cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
//    @JoinColumn(name = "prescription_detail_id")
//    private PrescriptionDetail prescriptionDetail;

}
