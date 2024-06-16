package com.edu.barcodeapp.service.impl;

import com.edu.barcodeapp.dto.BarCodeDto;
import com.edu.barcodeapp.dto.requestDto.RequestBarCodeDto;
import com.edu.barcodeapp.dto.responseDto.BarCodeResponseDto;
import com.edu.barcodeapp.dto.responseDto.CommonResponseDto;
import com.edu.barcodeapp.dto.responseDto.paginated.PaginatedResponseBarCodeDto;
import com.edu.barcodeapp.entity.Barcode;
import com.edu.barcodeapp.exception.EntryNotFoundException;
import com.edu.barcodeapp.repo.BarCodeRepo;
import com.edu.barcodeapp.service.BarCodeService;
import com.edu.barcodeapp.utill.Generator;
import com.edu.barcodeapp.utill.mapper.BarCodeMapper;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BarCodeRegistryImpl implements BarCodeService {

    @Autowired
    private Generator generator;

    @Autowired
    private BarCodeMapper barCodeMapper;

    @Autowired
    private BarCodeRepo barCodeRepo;


    @Override
    public CommonResponseDto saveCourse(RequestBarCodeDto dto) {
        try {

            String LastBarCodeLabel = barCodeRepo.getNextNumber();

            String barCodeTableId =  generator.generateFourNumbers();
            String barCodeLabel = "DH" + "-" +LastBarCodeLabel;

            String filePath = "src/main/resources/images/"+barCodeTableId+".png";

            int width = 300;
            int height = 100;

            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new Code128Writer().encode(barCodeLabel, BarcodeFormat.CODE_128, width, height, hints);


            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", new File(filePath).toPath());
            byte[] imageData = Files.readAllBytes(new File(filePath).toPath());

            BarCodeDto barCodeDto = new BarCodeDto(
                    barCodeTableId,
                    barCodeLabel,
                    imageData,
                    dto.getItemAssetNumber(),
                    dto.getItemName()
            );
            barCodeRepo.save(barCodeMapper.dtoToBarCodeEntity(barCodeDto));

            return new CommonResponseDto(201, "BarCode saved!", barCodeDto.getBarCodeValue(), new ArrayList<>());
        }catch (Exception e){
            throw new EntryNotFoundException("Can't Save because of this Error -->  " + e);
        }
    }

    @Override
    public PaginatedResponseBarCodeDto allBarCodes() throws SQLException {
        try {
            List<Barcode> allBarCodes = barCodeRepo.getAllBarCodes();
            List<BarCodeResponseDto> barCodeResponseDtos = new ArrayList<>();

            for (Barcode b : allBarCodes) {
                barCodeResponseDtos.add(
                        new BarCodeResponseDto(
                                b.getId(),
                                b.getBarCodeValue(),
                                b.getCodeImg(),
                                b.getItemAssetNumber(),
                                b.getItemName()
                        )
                );
            }
            return new PaginatedResponseBarCodeDto(
                    barCodeRepo.count(),
                    barCodeResponseDtos
            );
        }catch (Exception e){
            throw new EntryNotFoundException("Can't find any data...!");
        }
    }

}
