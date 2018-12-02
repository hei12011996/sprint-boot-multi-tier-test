package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingLotResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

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

    @GetMapping(path = "/{plId}")
    public ResponseEntity<ParkingLotResponse> get(@PathVariable Long plId) {
        final Optional<ParkingLot> parkingLotRecord = parkingLotRepository.findById(plId);
        if(!parkingLotRecord.isPresent()){
            return ResponseEntity.notFound().build();
        }
        final ParkingLotResponse parkingBoy = ParkingLotResponse.create(parkingLotRecord.get());
        return ResponseEntity.ok(parkingBoy);
    }

    @PostMapping
    public ResponseEntity<ParkingLotResponse> add(@RequestBody ParkingLot parkingLot) {
        if (!parkingLot.isValid()) {
            return ResponseEntity.badRequest().build();
        }
        final ParkingLot conflictedParkingLot = parkingLotRepository.findByParkingLotId(parkingLot.getParkingLotId());
        if (conflictedParkingLot != null) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .location(URI.create("/parkinglots/" + conflictedParkingLot.getId()))
                    .build();
        }
        parkingLot.setAvailablePositionCount(parkingLot.getCapacity());
        final ParkingLot savedParkingLot = parkingLotRepository.saveAndFlush(parkingLot);
        return ResponseEntity.created(URI.create("/parkinglots/" + savedParkingLot.getId())).build();
    }
}
