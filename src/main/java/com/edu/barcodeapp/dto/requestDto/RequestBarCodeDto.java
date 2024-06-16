package com.edu.barcodeapp.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class RequestBarCodeDto {

    private String itemAssetNumber;
    private String itemName;
    private byte[] barCodeImage;
}
