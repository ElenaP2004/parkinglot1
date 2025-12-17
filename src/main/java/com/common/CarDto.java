package com.common;

public class CarDto {
    Long id;
    String licensePlate;
    String parkingSpot;
    String ownerName;

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public Long getId() {
        return id;
    }

    public CarDto(String ownerName, Long id, String licensePlate, String parkingSpot) {
        this.ownerName = ownerName;
        this.id = id;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
    }


}
