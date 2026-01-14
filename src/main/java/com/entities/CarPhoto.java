package com.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "car_photo")
public class CarPhoto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ NECESAR PENTRU MySQL
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "file_type")
    private String fileType;

    @Lob
    @Column(name = "photo")
    private byte[] fileContent;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    // --- Getters și Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}