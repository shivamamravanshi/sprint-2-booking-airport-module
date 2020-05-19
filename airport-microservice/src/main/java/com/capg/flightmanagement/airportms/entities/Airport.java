package com.capg.flightmanagement.airportms.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/***
 * @author Shivam Amravanshi
 */
@Entity
@Table(name = "airports")
public class Airport {

    String airportName;

    @Id
    String airportCode;

    String airportLocation;

    /**
     * default Non parametrized constructor
     */
    public Airport() {

    }

    /**
     *
     * @param airportName   Initialize airport Name
     * @param airportCode   Initialize airport code
     * @param airportLocation   Initialize airportLocation
     *
     */
    public Airport(String airportName, String airportCode, String airportLocation) {
        this.airportName = airportName;
        this.airportCode = airportCode;
        this.airportLocation = airportLocation;
    }


    /**
     *
     * @return airport Name
     */
    public String getAirportName() {
        return airportName;
    }

    /**
     * set Airport Name
     * @param airportName
     */
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    /**
     *
     * @return airportCode
     */
    public String getAirportCode() {
        return airportCode;
    }

    /**
     * set Airport code
     * @param airportCode
     */
    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    /**
     *
     * @return airport Location
     */
    public String getAirportLocation() {
        return airportLocation;
    }

    /**
     *  Set Airport Location
     * @param airportLocation
     */
    public void setAirportLocation(String airportLocation) {
        this.airportLocation = airportLocation;
    }

    /**
     *  check equality of airport object
     * @param object
     * @return
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || !(object instanceof Airport)) return false;
        Airport airport = (Airport) object;
        return this.airportCode.equals(airport.getAirportCode());
    }

    /**
     * override hashcode
     * @return hashcode
     */
    @Override
    public int hashCode() {
        return airportCode.hashCode();
    }

    /**
     *
     * @return combine details of airport
     */
    @Override
    public String toString() {
        return "Airport Name :" + airportName + " " + "Airport Location :" + airportLocation + " " + "Airport Code :" + airportLocation;
    }

}
