package com.sparrow.backend.model;

import javax.persistence.*;

enum CarType{
    Diesel , Petrol , Electric
}

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private CarType carType;

    private String carManufacturer;

    private String carModel;

    private Integer YearOfManufacturing;

    private Boolean available;

    private double price;

    @ManyToOne
    private RentACar rentACar;

    public Car(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    public String getCarManufacturer() {
        return carManufacturer;
    }

    public void setCarManufacturer(String carManufacturer) {
        this.carManufacturer = carManufacturer;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public Integer getYearOfManufacturing() {
        return YearOfManufacturing;
    }

    public void setYearOfManufacturing(Integer yearOfManufacturing) {
        YearOfManufacturing = yearOfManufacturing;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RentACar getRentACar() {
        return rentACar;
    }

    public void setRentACar(RentACar rentACar) {
        this.rentACar = rentACar;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}

