package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.Objects;

public class ParkingLotResponse {
    private String parkingLotID;
    private Integer capacity;
    private Integer availablePositionCount;

    public static ParkingLotResponse create(String parkingLotID, Integer capacity, Integer availablePositionCount) {
        Objects.requireNonNull(parkingLotID);
        Objects.requireNonNull(capacity);
        Objects.requireNonNull(availablePositionCount);

        final ParkingLotResponse response = new ParkingLotResponse();
        response.setParkingLotID(parkingLotID);
        response.setCapacity(capacity);
        response.setAvailablePositionCount(availablePositionCount);
        return response;
    }

    public static ParkingLotResponse create(ParkingLot entity) {
        return create(entity.getParkingLotId(), entity.getCapacity(), entity.getAvailablePositionCount());
    }

    @JsonIgnore
    public boolean isValid() {
        return parkingLotID != null && capacity != null && availablePositionCount != null;
    }

    public String getParkingLotID() {
        return parkingLotID;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getAvailablePositionCount() {
        return availablePositionCount;
    }

    public void setParkingLotID(String parkingLotID) {
        this.parkingLotID = parkingLotID;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setAvailablePositionCount(Integer availablePositionCount) {
        this.availablePositionCount = availablePositionCount;
    }
}
