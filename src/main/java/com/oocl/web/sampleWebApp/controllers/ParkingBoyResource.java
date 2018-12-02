package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.domain.ParkingLot;
import com.oocl.web.sampleWebApp.domain.ParkingLotRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyParkingLotsAssociationResponse;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/parkingboys")
public class ParkingBoyResource {

    private ParkingBoyRepository parkingBoyRepository;
    private ParkingLotRepository parkingLotRepository;

    @Autowired
    public ParkingBoyResource(ParkingBoyRepository parkingBoyRepository, ParkingLotRepository parkingLotRepository){
        this.parkingBoyRepository = parkingBoyRepository;
        this.parkingLotRepository = parkingLotRepository;
    }

    @GetMapping
    public ResponseEntity<ParkingBoyResponse[]> getAll() {
        final ParkingBoyResponse[] parkingBoys = parkingBoyRepository.findAll().stream()
            .map(ParkingBoyResponse::create)
            .toArray(ParkingBoyResponse[]::new);
        return ResponseEntity.ok(parkingBoys);
    }

    @PostMapping
    public ResponseEntity<ParkingBoyResponse> add(@RequestBody ParkingBoy parkingBoy) {
        if((!parkingBoy.isValid()) || (parkingBoyRepository.findByEmployeeId(parkingBoy.getEmployeeId()) != null)){
            return ResponseEntity.badRequest().build();
        }
        final ParkingBoy savedParkingBoy = parkingBoyRepository.saveAndFlush(parkingBoy);
        return ResponseEntity.created(URI.create("/parkingboys/" + savedParkingBoy.getId())).build();
    }

    @PostMapping(path = "/{pbId}/parkinglots/{plId}")
    public ResponseEntity<ParkingBoyResponse> associateParkingLot(@PathVariable Long pbId, @PathVariable Long plId) {
        if(!parkingBoyRepository.findById(pbId).isPresent()|| !parkingLotRepository.findById(plId).isPresent()){
            return ResponseEntity.badRequest().build();
        }
        final ParkingLot parkingLot = parkingLotRepository.findById(plId).get();
        parkingLot.setParkingBoyId(pbId);
        final ParkingLot savedParkingLot = parkingLotRepository.saveAndFlush(parkingLot);
        return ResponseEntity.created(URI.create("/parkingboys/" + pbId + "/parkinglots/" + plId)).build();
    }
}
