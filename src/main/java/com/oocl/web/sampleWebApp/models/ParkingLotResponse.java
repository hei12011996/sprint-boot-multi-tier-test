package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.Objects;

public class ParkingLotResponse {
    private String parkingLotId;
    private Integer capacity;
    private Integer availablePositionCount;

    private static ParkingLotResponse create(String parkingLotID, Integer capacity, Integer availablePositionCount) {
        Objects.requireNonNull(parkingLotID);
        Objects.requireNonNull(capacity);
        Objects.requireNonNull(availablePositionCount);

        final ParkingLotResponse response = new ParkingLotResponse();
        response.setParkingLotId(parkingLotID);
        response.setCapacity(capacity);
        response.setAvailablePositionCount(availablePositionCount);
        return response;
    }

    public static ParkingLotResponse create(ParkingLot entity) {
        return create(entity.getParkingLotId(), entity.getCapacity(), entity.getAvailablePositionCount());
    }

    @JsonIgnore
    public boolean isValid() {
        return parkingLotId != null && capacity != null && availablePositionCount != null;
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public Integer getAvailablePositionCount() {
        return availablePositionCount;
    }

    private void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    private void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    private void setAvailablePositionCount(Integer availablePositionCount) {
        this.availablePositionCount = availablePositionCount;
    }
}
