package com.oocl.web.sampleWebApp.controllers;

import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingBoyRepository;
import com.oocl.web.sampleWebApp.models.ParkingBoyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/parkingboys")
public class ParkingBoyResource {

    private ParkingBoyRepository parkingBoyRepository;

    @Autowired
    public ParkingBoyResource(ParkingBoyRepository parkingBoyRepository){
        this.parkingBoyRepository = parkingBoyRepository;
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
}
