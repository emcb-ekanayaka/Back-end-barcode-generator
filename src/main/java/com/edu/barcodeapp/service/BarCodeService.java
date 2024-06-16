package com.edu.barcodeapp.service;

import com.edu.barcodeapp.dto.requestDto.RequestBarCodeDto;
import com.edu.barcodeapp.dto.responseDto.CommonResponseDto;
import com.edu.barcodeapp.dto.responseDto.paginated.PaginatedResponseBarCodeDto;

import java.sql.SQLException;

public interface BarCodeService {

    CommonResponseDto saveCourse(RequestBarCodeDto dto);

    PaginatedResponseBarCodeDto allBarCodes() throws SQLException;

}
