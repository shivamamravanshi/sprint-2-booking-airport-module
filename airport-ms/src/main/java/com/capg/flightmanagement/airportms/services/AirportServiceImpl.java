package com.capg.flightmanagement.airportms.services;

import java.util.*;

import com.capg.flightmanagement.airportms.dao.IAirportDao;
import com.capg.flightmanagement.airportms.entities.Airport;
import com.capg.flightmanagement.airportms.exceptions.InvalidAirportCodeException;
import com.capg.flightmanagement.airportms.exceptions.InvalidArgumentException;
import com.capg.flightmanagement.airportms.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/***
 * @author Shivam Amravanshi
 */
@Service
@Transactional
public class AirportServiceImpl implements IAirportService {
    private IAirportDao dao;

    public IAirportDao getDao() {
        return dao;
    }

    @Autowired
    public void setDao(IAirportDao dao) {
        this.dao = dao;
    }

    /**
     *
     * @param airport
     * This method will validate airport and add it to the database
     * @return airport
     */
    @Override
    public Airport addAirport(Airport airport) {
        if(airport==null){
            throw new InvalidArgumentException("Airport can't be null");
        }
        airport = dao.save(airport);
        return airport;
    }

    /**
     *  This method will return list of all airport
     * @return List of airports
     */
    @Override
    public List<Airport> viewAllAirports() {
        List<Airport> airportList = dao.findAll();
        return airportList;
    }

    /**
     *
     * @param airportCode
     * This method will fetch the Airport by airportCode
     * @return
     */
    @Override
    public Airport viewAirport(String airportCode) {
        Validation.validateCode(airportCode);
        Optional<Airport> optionalAirport = dao.findById(airportCode);
        if (optionalAirport.isPresent()) {
            Airport airport = optionalAirport.get();
            return airport;
        }
        throw new InvalidAirportCodeException("Invalid Airport Code, Airport Not Exist for given code");

    }

}

