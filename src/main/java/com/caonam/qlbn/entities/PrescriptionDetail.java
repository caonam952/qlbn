package com.caonam.qlbn.entities;

import com.caonam.qlbn.dto.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "prescription_detail")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PrescriptionDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

    //số lượng thuốc
    @Column(name = "amount")
    private int amount;

    //liều dùng
    @Column(name = "dosage")
    private String dosage;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;

    @OneToOne(
//            mappedBy = "prescriptionDetail",
            fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;



}
