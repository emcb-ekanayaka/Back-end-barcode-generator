package com.edu.barcodeapp.utill.mapper;

import com.edu.barcodeapp.dto.BarCodeDto;
import com.edu.barcodeapp.entity.Barcode;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper(componentModel = "spring")
public interface BarCodeMapper {
    Barcode dtoToBarCodeEntity(BarCodeDto barCodeDto);
}
