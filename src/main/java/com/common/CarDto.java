package com.common;

public class CarDto {
    private Long id;
    private String licensePlate;
    private String parkingSpot;
    private String ownerName;

    // Constructorul default (fără argumente) este adesea necesar în Java EE
    public CarDto() {
    }

    // Constructorul complet
    public CarDto(Long id, String licensePlate, String parkingSpot, String ownerName) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.parkingSpot = parkingSpot;
        this.ownerName = ownerName;
    }

    // --- GETTERS ---
    public Long getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getParkingSpot() {
        return parkingSpot;
    }

    public String getOwnerName() {
        return ownerName;
    }

    // --- SETTERS (Adăugate pentru o mapare mai ușoară în CarsBean) ---
    public void setId(Long id) {
        this.id = id;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public void setParkingSpot(String parkingSpot) {
        this.parkingSpot = parkingSpot;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

}


