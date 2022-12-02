package com.caonam.qlbn.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//bệnh án
@Entity
@Table(name = "record")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Record {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "uuid-char")
    private UUID id;

//    //tiền sử bệnh
//    @Column(name = "heath_history")
//    private String healthHistory;

    //bệnh sử
    @Column(name = "medical_history")
    private String medicalHistory;

    //sản phẩm đang dùng
    @Column(name = "product_in_use")
    private String productInUse;

    //chẩn đoán
    @Column(name = "diagnose")
    private String diagnose;

    //kết quả khám
    @Column(name = "result")
    private String result;

    //phác đồ
    @Column(name = "regimen")
    private String regimen;

    @Column(name = "pre_image")
    private String preImage;

    @Column(name = "after_image")
    private String afterImage;

    @Column(name = "create_at")
    private Date createAt;

    @OneToOne(
//            mappedBy = "record",
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "patient_id")
    private Patient patient;

}
