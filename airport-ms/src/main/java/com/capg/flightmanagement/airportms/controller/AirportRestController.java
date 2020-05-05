package com.capg.flightmanagement.airportms.controller;

import com.capg.flightmanagement.airportms.entities.Airport;
import com.capg.flightmanagement.airportms.exceptions.InvalidAirportCodeException;
import com.capg.flightmanagement.airportms.services.IAirportService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolationException;
import java.util.List;

@RequestMapping("/airports")
@RestController
public class AirportRestController {
    /**
     * Record the log in file
     */
    private static final Logger Log = LoggerFactory.getLogger(AirportRestController.class);

    @Autowired
    IAirportService airportService;

    /**
     * This method will add all the airport to the database
     */
    @PostConstruct
    public void addAirports() {
        Airport airport = new Airport();
        airport.setAirportLocation("Mumbai");
        airport.setAirportCode("BOM");
        airport.setAirportName("Mumbai International Airport");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Bhopal Airport");
        airport.setAirportCode("BHO");
        airport.setAirportLocation("Bhopal");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportLocation("Bangalore");
        airport.setAirportName("Bangalore International Airport");
        airport.setAirportCode("BLR");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Devi Ahilyabai Holkar Airport");
        airport.setAirportCode("IDR");
        airport.setAirportLocation("Indore");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Indira Gandhi International Airport");
        airport.setAirportLocation("New Delhi");
        airport.setAirportCode("DEL");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportLocation("Jabalpur");
        airport.setAirportCode("JLR");
        airport.setAirportName("Jabalpur Airport");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Lohegaon Airport");
        airport.setAirportLocation("Pune");
        airport.setAirportCode("PNQ");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Netaji Subhash Chandra Bose International Airport");
        airport.setAirportLocation("Kolkata");
        airport.setAirportCode("CCU");
        airportService.addAirport(airport);

        airport = new Airport();
        airport.setAirportName("Srinagar Airport");
        airport.setAirportCode("SXR");
        airport.setAirportLocation("Srinagar");
        airportService.addAirport(airport);
    }

    /**
     * fetch all the airports from database
     * @return airport list and response to server
     */
    @GetMapping()
    public ResponseEntity<List<Airport>> fetchAllAirports() {
        List<Airport> airportList = airportService.viewAllAirports();
        ResponseEntity<List<Airport>> response = new ResponseEntity<>(airportList, HttpStatus.OK);
        return response;
    }

    /**
     * fetch airport object by airport code
     * @param airportCode
     * @return airport and response to server
     */
    @GetMapping("/get/{airportCode}")
    public ResponseEntity<Airport> fetchAirportByCode(@PathVariable("airportCode") String airportCode) {
        Airport airport = airportService.viewAirport(airportCode);
        ResponseEntity<Airport> response = new ResponseEntity<>(airport, HttpStatus.OK);
        return response;
    }

    /**
     * this method will run when InvalidAirportCodeException occur
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidAirportCodeException.class)
    public ResponseEntity<String> handleEmployeeNotFound(InvalidAirportCodeException ex) {
        Log.error("Invalid Airport Code Exception ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
        return response;
    }

    /**
     * this method will run when ConstraintViolationException occur
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
        Log.error("constraint violation ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }

    /**
     * Blanket Exception Handler
     * @param ex
     * @return
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("Something went wrong ", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }

}
