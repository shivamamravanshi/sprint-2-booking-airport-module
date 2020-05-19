package com.capg.flightmanagement.bookingms.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Random;
import java.util.UUID;

/***
 * @author Shivam Amravanshi
 *
 * This class is use to transfer the Passenger Details
 */
public class PassengerDetailsDto {

    private BigInteger pnrNumber;
    private String passengerName;
    @Min(10)
    @Max(99)
    private Integer passengerAge;

    private BigInteger passengerUIN;
    private String gender;
    private int luggage;

    private static Long generatedNumber = 1000000000L;
    public PassengerDetailsDto(){
        this.pnrNumber = generateValue();
    }

    /***
     * Generate Unique PNR Number
     * @return PNR number as BigInteger
     */
    private static BigInteger generateValue(){
        String generatedValue = ++generatedNumber+"";
        return new BigInteger(generatedValue);
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public BigInteger getPnrNumber() {
        return pnrNumber;
    }

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

    public int getLuggage() {
        return luggage;
    }

    public void setLuggage(int luggage) {
        this.luggage = luggage;
    }
}
