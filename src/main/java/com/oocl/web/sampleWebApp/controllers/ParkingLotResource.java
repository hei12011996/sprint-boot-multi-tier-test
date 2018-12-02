package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotResource {

    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingLotResource(ParkingLotRepository parkingLotRepository){
        this.parkingLotRepository = parkingLotRepository;
    }

    @GetMapping
    public ResponseEntity<ParkingLotResponse[]> getAll() {
        final ParkingLotResponse[] parkingLots = parkingLotRepository.findAll().stream()
                .map(ParkingLotResponse::create)
                .toArray(ParkingLotResponse[]::new);
        return ResponseEntity.ok(parkingLots);
    }

    @PostMapping
    public ResponseEntity<ParkingLotResponse> add(@RequestBody ParkingLot parkingLot) {
        if(parkingLotRepository.findByParkingLotId(parkingLot.getParkingLotId()) != null){
            return ResponseEntity.badRequest().build();
        }
        final ParkingLot savedParkingLot = parkingLotRepository.saveAndFlush(parkingLot);
        return ResponseEntity.created(URI.create("/parkinglots/" + savedParkingLot.getId())).build();
    }
}
