package com.capg.flightmanagement.bookingms.dto;

import java.math.BigInteger;

public class FlightAndScheduleInfoDto {
    private BigInteger flightNumber;
    private String carrierName;
    private String flightModel;
    private String departureTime;
    private String arrivalTime;
    private double seatCost;

    public BigInteger getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(BigInteger flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getFlightModel() {
        return flightModel;
    }

    public void setFlightModel(String flightModel) {
        this.flightModel = flightModel;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public double getSeatCost() {
        return seatCost;
    }

    public void setSeatCost(double seatCost) {
        this.seatCost = seatCost;
    }
}
