package com.capg.flightmanagement.bookingms.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigInteger;

public class PassengerDetailsDto {
    private BigInteger pnrNumber;
    private String passengerName;
    private Integer passengerAge;
    private BigInteger passengerUIN;
    private String gender;

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigInteger getPnrNumber() {
        return pnrNumber;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public void setPnrNumber(BigInteger pnrNumber) {
        this.pnrNumber = pnrNumber;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public Integer getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(Integer passengerAge) {
        this.passengerAge = passengerAge;
    }

    public BigInteger getPassengerUIN() {
        return passengerUIN;
    }

    public void setPassengerUIN(BigInteger passengerUIN) {
        this.passengerUIN = passengerUIN;
    }
}
