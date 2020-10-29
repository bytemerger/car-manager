package com.api.vehicle.service;

public class CarNotFoundException extends RuntimeException{
    public CarNotFoundException(){

    }
    public CarNotFoundException(String message) {
        super(message);
    }
}
