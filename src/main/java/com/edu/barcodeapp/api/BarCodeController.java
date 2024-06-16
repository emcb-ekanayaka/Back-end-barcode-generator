package com.edu.barcodeapp.api;

import com.edu.barcodeapp.dto.requestDto.RequestBarCodeDto;
import com.edu.barcodeapp.dto.responseDto.CommonResponseDto;
import com.edu.barcodeapp.service.BarCodeService;
import com.edu.barcodeapp.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/barcode")
public class BarCodeController {

    @Autowired
    private BarCodeService barCodeService;

    @PostMapping
    public ResponseEntity<StandardResponse> savedBarcode(@RequestBody RequestBarCodeDto data){

        CommonResponseDto responseData = barCodeService.saveCourse(data);
        return new ResponseEntity<>(
                new StandardResponse(
                        responseData.getCode(),
                        responseData.getMessage(),
                        responseData.getData()
                ),
                HttpStatus.CREATED
        );
    }

    @GetMapping
    public ResponseEntity<StandardResponse> getAllBarcodes()throws SQLException {
        return new ResponseEntity<>(
                new StandardResponse(
                        200,
                        "BarCode List",
                        barCodeService.allBarCodes()),
                HttpStatus.OK
        );
    }


}
