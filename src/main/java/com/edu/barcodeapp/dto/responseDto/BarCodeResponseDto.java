package com.edu.barcodeapp.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BarCodeResponseDto {

    private String id;
    private String barCodeValue;
    private byte[] codeImg;
    private String itemAssetNumber;
    private String itemName;
}
