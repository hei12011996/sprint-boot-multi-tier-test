package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingBoyParkingLotsAssociationResponse {
    private String employeeId;
    private List<String> associatedParkingLots;

    private static ParkingBoyParkingLotsAssociationResponse create(String employeeId, List<String> associatedParkingLots) {
        Objects.requireNonNull(employeeId);
        Objects.requireNonNull(associatedParkingLots);

        final ParkingBoyParkingLotsAssociationResponse response = new ParkingBoyParkingLotsAssociationResponse();
        response.setEmployeeId(employeeId);
        response.setAssociatedParkingLots(associatedParkingLots);
        return response;
    }

    public static ParkingBoyParkingLotsAssociationResponse create(ParkingBoy parkingBoy, List<ParkingLot> parkingLots) {
        return create(parkingBoy.getEmployeeId(), parkingLots.stream().map(ParkingLot::getParkingLotId).collect(Collectors.toList()));
    }

    @JsonIgnore
    public boolean isValid() {
        return employeeId != null && associatedParkingLots != null;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public List<String> getAssociatedParkingLots() {
        return associatedParkingLots;
    }

    private void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    private void setAssociatedParkingLots(List<String> associatedParkingLots) {
        this.associatedParkingLots = associatedParkingLots;
    }
}
