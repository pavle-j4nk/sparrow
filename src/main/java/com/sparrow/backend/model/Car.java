package com.sparrow.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String carType;

    private String carManufacturer;

    private String carModel;

    private Integer YearOfManufacturing;

    private double price;

    @ManyToOne
    @JsonIgnore
    private RentACar rentACar;

    private Integer seats;

    public Car(String carType, String carManufacturer, String carModel, Integer yearOfManufacturing, double price, RentACar rentACar, Integer seats) {
        this.carType = carType;
        this.carManufacturer = carManufacturer;
        this.carModel = carModel;
        YearOfManufacturing = yearOfManufacturing;
        this.price = price;
        this.rentACar = rentACar;
        this.seats = seats;
    }

    public Car(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
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

}

