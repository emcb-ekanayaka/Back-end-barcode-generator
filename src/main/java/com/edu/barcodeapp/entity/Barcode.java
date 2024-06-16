package com.edu.barcodeapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "barcodetbl")
public class Barcode {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="itemAssetNumber")
    private String itemAssetNumber;

    @Column(name="itemName")
    private String itemName;

    @Column(name="barcodevalue")
    private String barCodeValue;

    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="codeimg")
    private byte[] codeImg;
//
//    @Column(name="codeimg")
//    private String codeImg;

}
