package com.edu.barcodeapp.dto.responseDto.paginated;

import com.edu.barcodeapp.dto.responseDto.BarCodeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponseBarCodeDto {
    private Long count;
    private List<BarCodeResponseDto> dataList;
}
