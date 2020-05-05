package com.capg.flightmanagement.airportms.services;
import java.util.List;

import com.capg.flightmanagement.airportms.entities.Airport;

public interface IAirportService{

	public Airport addAirport(Airport airport);
	public List<Airport> viewAllAirports();
	public Airport viewAirport(String airportCode);
}
