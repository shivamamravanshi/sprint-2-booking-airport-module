package com.capg.flightmanagement.airportms.dao;
import java.util.*;

import com.capg.flightmanagement.airportms.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IAirportDao extends JpaRepository<Airport,String> {
	
}
