package com.capg.flightmanagement.airportms.util;
import com.capg.flightmanagement.airportms.exceptions.InvalidArgumentException;

public class Validation {
	public static void validateCode(String airportCode) {
		if(airportCode==null) {
			throw new InvalidArgumentException("Passed Value should not be null");
		}
	}
	
}
