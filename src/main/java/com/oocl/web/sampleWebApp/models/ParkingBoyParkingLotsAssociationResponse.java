package com.oocl.web.sampleWebApp.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oocl.web.sampleWebApp.domain.ParkingBoy;
import com.oocl.web.sampleWebApp.domain.ParkingLot;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ParkingBoyParkingLotsAssociationResponse {
    private String employeeId;
    private List<ParkingLotAssociationResponse> associatedParkingLots;

    private static ParkingBoyParkingLotsAssociationResponse create(String employeeId, List<ParkingLotAssociationResponse> associatedParkingLots) {
        Objects.requireNonNull(employeeId);
        Objects.requireNonNull(associatedParkingLots);

        final ParkingBoyParkingLotsAssociationResponse response = new ParkingBoyParkingLotsAssociationResponse();
        response.setEmployeeId(employeeId);
        response.setAssociatedParkingLots(associatedParkingLots);
        return response;
    }

    public static ParkingBoyParkingLotsAssociationResponse create(ParkingBoy parkingBoy, List<ParkingLot> parkingLots) {
        List<ParkingLotAssociationResponse> associatedParkingLots = parkingLots.stream().map(ParkingLotAssociationResponse::create).collect(Collectors.toList());
        return create(parkingBoy.getEmployeeId(), associatedParkingLots);
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public List<ParkingLotAssociationResponse> getAssociatedParkingLots() {
        return associatedParkingLots;
    }

    private void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    private void setAssociatedParkingLots(List<ParkingLotAssociationResponse> associatedParkingLots) {
        this.associatedParkingLots = associatedParkingLots;
    }
}
