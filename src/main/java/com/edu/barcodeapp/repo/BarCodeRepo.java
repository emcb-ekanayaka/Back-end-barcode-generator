package com.edu.barcodeapp.repo;

import com.edu.barcodeapp.entity.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarCodeRepo extends JpaRepository<Barcode,Integer> {

    @Query(value = "SELECT lpad(substring(max(b.barcodevalue),4)+1,'4', '0') FROM generatecode.barcodetbl as b", nativeQuery = true)
    String getNextNumber();

    @Query(value = "SELECT * FROM generatecode.barcodetbl order by barcodevalue DESC limit 2", nativeQuery = true)
    List<Barcode> getAllBarCodes();

}
