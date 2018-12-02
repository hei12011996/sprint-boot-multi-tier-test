package com.oocl.web.sampleWebApp.domain;

import javax.persistence.*;

@Entity
@Table(name = "parking_lot")
public class ParkingLot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parking_lot_id", length = 64, unique = true, nullable = false)
    private String parkingLotId;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    @Column(name = "available_position_count", nullable = false)
    private Integer availablePositionCount;

    @Column(name = "parking_boy_id")
    private Long parkingBoyId;

    protected ParkingLot() {}

    public ParkingLot(String parkingLotID, Integer capacity) {
        this.parkingLotId = parkingLotID;
        this.capacity = capacity;
        this.availablePositionCount = capacity;
    }

    public Long getId() {
        return id;
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

    public Long getParkingBoyId() {
        return parkingBoyId;
    }

    public void setParkingLotId(String parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setAvailablePositionCount(Integer availablePositionCount) {
        this.availablePositionCount = availablePositionCount;
    }

    public void setParkingBoyId(Long parkingBoyId) {
        this.parkingBoyId = parkingBoyId;
    }

    public Boolean isValid(){
        return parkingLotId != null && capacity >= 1 && capacity <= 100;
    }
}

