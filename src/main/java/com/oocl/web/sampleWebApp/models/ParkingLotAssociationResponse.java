package com.oocl.web.sampleWebApp.models;

import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.Objects;

public class ParkingLotAssociationResponse {
    private String parkingLotId;

    private static ParkingLotAssociationResponse create(String parkingLotId) {
        Objects.requireNonNull(parkingLotId);

        final ParkingLotAssociationResponse response = new ParkingLotAssociationResponse();
        response.setParkingLotId(parkingLotId);
        return response;
    }

    static ParkingLotAssociationResponse create(ParkingLot parkingLot) {
        return create(parkingLot.getParkingLotId());
    }

    public String getParkingLotId() {
        return parkingLotId;
    }

    private void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }
}
