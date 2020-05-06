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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
/*
    @Value("${userService.baseUrl}")
    private String userServiceBaseUrl;

    @Value("${flightScheduleService.baseUrl")
    private String flightScheduleServiceBaseUrl;

    @Value("${passengerService.baseUrl")
    private String passengerServiceBaseUrl;
*/
    @PostMapping("/new")
    public ResponseEntity<BookingDetailsDto> createBookingRequest(@RequestBody BookingRequestDto bookingRequestDto){
        BookingDetailsDto bookingDetailsDto = convertToResponseDto(bookingRequestDto);
        Booking booking = new Booking();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
        LocalDate bookingDate = LocalDate.parse(bookingRequestDto.getBookingDate(),formatter);

        booking.setBookingDate(bookingDate);
        booking.setFlightNumber(bookingRequestDto.getFlightNumber());
        booking.setTicketCost(bookingRequestDto.getTicketCost());
        booking.setUserId(bookingDetailsDto.getUserId());

        List<BigInteger> passengerUINList = passengerUINList(bookingRequestDto);

        booking.setPassengersUINList(passengerUINList);
        booking.setNoOfPassenger(passengerUINList.size());
        booking = bookingService.addBooking(booking);
        acknowledgeBooking(booking);
        requestPassengerStore(bookingDetailsDto.getPassengerList());

        ResponseEntity<BookingDetailsDto> response = new ResponseEntity<>(bookingDetailsDto,HttpStatus.OK);
        return response;
    }

    private void acknowledgeBooking(Booking booking){
/*
        String url = flightScheduleServiceBaseUrl+"/booked";
        restTemplate.put(url,booking);
*/
    }

    private void requestPassengerStore(List<PassengerDetailsDto> passengerDetailsDtoList){
/*
        String url = passengerServiceBaseUrl+"/store";
        restTemplate.put(url,passengerDetailsDtoList);
*/
    }

    private List<BigInteger> passengerUINList(BookingRequestDto bookingRequestDto){
        List<BigInteger> passengerUINList = new ArrayList<>();
        List<PassengerDetailsDto> passengerList = bookingRequestDto.getPassengerList();
        for (PassengerDetailsDto passenger: passengerList) {
            passengerUINList.add(passenger.getPassengerUIN());
        }
        return passengerUINList;
    }

    private BookingDetailsDto convertToResponseDto(BookingRequestDto bookingRequestDto){
        BookingDetailsDto bookingDetailsDto = new BookingDetailsDto();
        bookingDetailsDto.setPassengerList(bookingRequestDto.getPassengerList());
        bookingDetailsDto.setFlightNumber(bookingRequestDto.getFlightNumber());
        bookingDetailsDto.setSource(bookingRequestDto.getSource());
        bookingDetailsDto.setDestination(bookingRequestDto.getDestination());
        bookingDetailsDto.setBillingAddress(bookingRequestDto.getBillingAddress());
        //schedule
        FlightScheduleDto flightScheduleDto = getScheduleFlightDetails(bookingRequestDto.getFlightNumber());

        if(bookingRequestDto.getPassengerList().size()>flightScheduleDto.getAvailableSeat())
        {
            throw new BookingFullException("Booking is full for flight Number "+bookingRequestDto.getFlightNumber()+"for Date "+bookingRequestDto.getBookingDate());
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");

        bookingDetailsDto.setArrivalTime(flightScheduleDto.getArrivalTime());
        bookingDetailsDto.setDepartureTime(flightScheduleDto.getDepartureTime());
        //userDetails
        UserDetailsDto userDetailsDto = fetchUserById(bookingRequestDto.getUserId());

        bookingDetailsDto.setUserId(userDetailsDto.getUserId());
        bookingDetailsDto.setUserPhone(userDetailsDto.getUserPhone());

        return bookingDetailsDto;
    }

    private FlightScheduleDto getScheduleFlightDetails(BigInteger flightNumber){
/*
        //Integrated
        String url = flightScheduleServiceBaseUrl +"/get/"+flightNumber;
        FlightScheduleDto  flightScheduleDto = restTemplate.getForObject(url,FlightScheduleDto.class);
        return flightScheduleDto;
*/
        //Isolation
        FlightScheduleDto flightScheduleDto = new FlightScheduleDto();
        flightScheduleDto.setAvailableSeat(10);
        flightScheduleDto.setFlightNumber(new BigInteger("1111"));
        flightScheduleDto.setArrivalTime("20:30");
        flightScheduleDto.setDepartureTime("21:30");
        //flightScheduleDto.setArrivalTime(LocalTime.of(20,30));
        //flightScheduleDto.setDepartureTime(LocalTime.of(21,00));
        return flightScheduleDto;
    }

    private UserDetailsDto fetchUserById(BigInteger userId) {
/*
        //Integrated
        String url = userServiceBaseUrl + "/get/" + userId;
        UserDetailsDto userDto = restTemplate.getForObject(url, UserDetailsDto.class);
        return userDto;
*/
        //Isolation
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setEmail("shivam123@gmail.com");
        userDetailsDto.setUserId(new BigInteger("100"));
        userDetailsDto.setUserName("Shivam");
        userDetailsDto.setUserPhone(new BigInteger("9770909000"));
        return userDetailsDto;
    }


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
        Booking booking = bookingService.viewBooking(bookingId);
        ResponseEntity<Boolean> response;
        if(booking != null){
            Boolean result = bookingService.deleteBooking(bookingId);
            response = new ResponseEntity<>(result, HttpStatus.OK);
            acknowledgeCancelBooking(booking);
            List<BigInteger> passengerUINList = booking.getPassengersUINList();
            cancelRequestPassengerStore(passengerUINList);
        }else
            response = new ResponseEntity<>(false,HttpStatus.NO_CONTENT);
        return response;
    }

    private void acknowledgeCancelBooking(Booking booking){
/*
        String url = flightScheduleServiceBaseUrl+"/canceled";
        restTemplate.put(url,booking);
*/
    }

    private void cancelRequestPassengerStore(List<BigInteger> passengerUINList){
/*
        String url = passengerServiceBaseUrl+"/canceled";
        restTemplate.put(url,passengerUINList);
*/
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
