package com.oocl.web.sampleWebApp.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long> {
    ParkingLot findByParkingLotId(String parkingLotId);
    List<ParkingLot> findByParkingBoyId(Long parkingBoyId);
}

