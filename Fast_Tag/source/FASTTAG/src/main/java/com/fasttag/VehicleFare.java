package com.fasttag;

public class VehicleFare
{
    private String vehicleType;
    private double fare;

    public VehicleFare(String vehicleType, double fare)
    {
        this.vehicleType = vehicleType;
        this.fare = fare;
    }

    public VehicleFare() {

    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

}
