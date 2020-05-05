package com.capg.flightmanagement.bookingms.controller;

import com.capg.flightmanagement.bookingms.dto.*;
import com.capg.flightmanagement.bookingms.entities.Booking;
import com.capg.flightmanagement.bookingms.exceptions.BookingFullException;
import com.capg.flightmanagement.bookingms.exceptions.BookingNotFoundException;
import com.capg.flightmanagement.bookingms.exceptions.InvalidBookingIdException;
import com.capg.flightmanagement.bookingms.services.IBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.ConstraintViolationException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingRestController {
    @Autowired
    private IBookingService bookingService;

    private static final Logger Log = LoggerFactory.getLogger(BookingRestController.class);

    @Autowired
    RestTemplate restTemplate;

    @Value("${userService.baseUrl}")
    private String userServiceBaseUrl;

    @Value("${userFlightScheduleService.baseUrl")
    private String flightScheduleServiceBaseUrl;

    @PostMapping("/newBooking")
    public ResponseEntity<BookingResponseDto> createBooking(@RequestBody BookingDto bookingDto){
        BookingResponseDto responseDto = convertToResponseDto(bookingDto);
        Booking booking = new Booking();
        booking.setBookingDate(bookingDto.getBookingDate());
        booking.setFlightNumber(bookingDto.getFlightNumber());
        booking.setTicketCost(12000);
        booking.setUserId(responseDto.getUserId());

        List<BigInteger> passengerUINList = passengerUINList(bookingDto);

        booking.setPassengersUIDList(passengerUINList);
        booking.setNoOfPassenger(passengerUINList.size());
        booking = bookingService.addBooking(booking);
        acknowledgeBooking(booking);

        ResponseEntity<BookingResponseDto> response = new ResponseEntity<>(responseDto,HttpStatus.OK);
        return response;
    }

    private void acknowledgeBooking(Booking booking){
        String url = flightScheduleServiceBaseUrl+"/booked";
        restTemplate.put(url,booking);
    }

    private List<BigInteger> passengerUINList(BookingDto bookingDto){
        List<BigInteger> passengerUINList = new ArrayList<>();
        List<PassengerDetailsDto> passengerList = bookingDto.getPassengerList();
        for (PassengerDetailsDto passenger: passengerList) {
            passengerUINList.add(passenger.getPassengerUIN());
        }
        return passengerUINList;
    }

    private BookingResponseDto convertToResponseDto(BookingDto bookingDto){
        BookingResponseDto bookingResponseDto = new BookingResponseDto();
        bookingResponseDto.setPassengerList(bookingDto.getPassengerList());
        bookingResponseDto.setFlightNumber(bookingDto.getFlightNumber());
        bookingResponseDto.setSource(bookingDto.getSource());
        bookingResponseDto.setDestination(bookingDto.getDestination());
        bookingResponseDto.setBillingAddress(bookingDto.getBillingAddress());
        //schedule
        FlightScheduleDto flightScheduleDto = getScheduleFlightDetails(bookingDto.getFlightNumber());

        if(bookingDto.getPassengerList().size()>flightScheduleDto.getAvailableSeat())
        {
            throw new BookingFullException("Booking is full for flight Number "+bookingDto.getFlightNumber()+"for Date "+bookingDto.getBookingDate());
        }

        bookingResponseDto.setArrivalTime(flightScheduleDto.getArrivalTime());
        bookingResponseDto.setDepartureTime(flightScheduleDto.getDepartureTime());
        //userDetails
        UserDetailsDto userDetailsDto = fetchUserById(bookingDto.getUserId());

        bookingResponseDto.setUserId(userDetailsDto.getUserId());
        bookingResponseDto.setUserPhone(userDetailsDto.getUserPhone());

        return bookingResponseDto;
    }

    private FlightScheduleDto getScheduleFlightDetails(BigInteger flightNumber){
        String url = flightScheduleServiceBaseUrl +"/get/"+flightNumber;
        FlightScheduleDto  flightScheduleDto = restTemplate.getForObject(url,FlightScheduleDto.class);
        return flightScheduleDto;
    }

    /*@PostMapping("/addBooking/{userId}")
    public ResponseEntity<Booking> createBooking(@RequestBody List<BookPassengerDto> bookPassengerDtos,
                                                 @PathVariable("userId") BigInteger userId,
                                                 @RequestBody BookingScheduleDetailsDto bookingScheduleDetailsDto) {
        UserDetailsDto userDto = fetchUserById(userId);
        Booking booking = convertBookPassengerDto(bookPassengerDtos);
        booking.setUserId(userDto.getUserId());
        booking.setTicketCost(12000);
        booking.setFlightNumber(bookingScheduleDetailsDto.getFlightNumber());
        booking.setBookingDate(bookingScheduleDetailsDto.getBookingDate());

        ResponseEntity<Booking> response = new ResponseEntity<>(booking,HttpStatus.OK);
        return response;
    }*/


    private UserDetailsDto fetchUserById(BigInteger userId) {
        String url = userServiceBaseUrl + "/get/" + userId;
        UserDetailsDto userDto = restTemplate.getForObject(url, UserDetailsDto.class);
        return userDto;
    }

    /*public Booking convertBookPassengerDto(List<BookPassengerDto> bookPassengerDtos) {
        List<BigInteger> passengerUINList = new ArrayList<>();
        int noOfPassengers = 0;
        Booking booking = new Booking();
        for (BookPassengerDto bookPassengerDto : bookPassengerDtos) {
            passengerUINList.add(bookPassengerDto.getPassengerUIN());
            noOfPassengers++;
        }
        booking.setPassengersUIDList(passengerUINList);
        booking.setNoOfPassenger(noOfPassengers);
        return booking;
    }*/

    @GetMapping("/get/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") BigInteger bookingId) {
        Booking booking = bookingService.viewBooking(bookingId);
        ResponseEntity<Booking> response = new ResponseEntity<>(booking, HttpStatus.OK);
        return response;
    }

    @GetMapping
    public ResponseEntity<List<Booking>> fetchAllBookings() {
        List<Booking> bookings = bookingService.viewAllBooking();
        ResponseEntity<List<Booking>> response = new ResponseEntity<>(bookings, HttpStatus.OK);
        return response;
    }

    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<Boolean> deleteBookingById(@PathVariable("bookingId") BigInteger bookingId) {
        Boolean result = bookingService.deleteBooking(bookingId);
        ResponseEntity<Boolean> response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @ExceptionHandler(InvalidBookingIdException.class)
    public ResponseEntity<String>handleEmployeeNotFound(InvalidBookingIdException ex){
        Log.error("Invalid Booking Id exception",ex);
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String>handleEmployeeNotFound(BookingNotFoundException ex){
        Log.error("Booking not found exception",ex);
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
        Log.error("constraint violation", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.BAD_REQUEST);
        return response;
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<String> handleAll(Throwable ex) {
        Log.error("Something went wrong", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
