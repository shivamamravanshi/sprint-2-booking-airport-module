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
import javax.validation.Valid;
import java.math.BigInteger;
import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/bookings")
/***
 * @author Shivam Amravanshi
 */
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

    @Value("${airportService.baseUrl}")
    private String airportServiceBaseUrl;

    /***
     * create Booking if new req
     * @param bookingRequestDto
     * @return
     */
    @PostMapping("/new")
    public ResponseEntity<BookingDetailsDto> createBookingRequest(@RequestBody @Valid BookingRequestDto bookingRequestDto){
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
        bookingService.validateBooking(booking);
        booking = bookingService.addBooking(booking);
        acknowledgeBooking(booking);
        requestPassengerStore(bookingDetailsDto.getPassengerList());

        ResponseEntity<BookingDetailsDto> response = new ResponseEntity<>(bookingDetailsDto,HttpStatus.OK);
        return response;
    }

    /***
     * this method will send the acknowledge to flightSchedule along with booking object
     * @param booking
     */
    private void acknowledgeBooking(Booking booking){
/*
        String url = flightScheduleServiceBaseUrl+"/booked";
        restTemplate.put(url,booking);
*/
    }

    /***
     * this method will send the list of passengers to Passenger service to store passengers Details to database
     * @param passengerDetailsDtoList
     */
    private void requestPassengerStore(List<PassengerDetailsDto> passengerDetailsDtoList){
/*
        String url = passengerServiceBaseUrl+"/store";
        restTemplate.put(url,passengerDetailsDtoList);
*/
    }

    /***
     *
     * @param bookingRequestDto
     * @return the list of passengers unique Identification Number
     */
    private List<BigInteger> passengerUINList(BookingRequestDto bookingRequestDto){
        List<BigInteger> passengerUINList = new ArrayList<>();
        List<PassengerDetailsDto> passengerList = bookingRequestDto.getPassengerList();
        for (PassengerDetailsDto passenger: passengerList) {
            passengerUINList.add(passenger.getPassengerUIN());
        }
        return passengerUINList;
    }

    /***
     * convert the booking request to booking details
     * @param bookingRequestDto
     * @return
     */
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

    /***
     * fetch the schedule information by flightNumber
     * @param flightNumber
     * @return
     */
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

    /***
     * fetch the user Details by unique user Id
     * @param userId
     * @return
     */
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


    /***
     * fetch the booking details from database and send response with booking details
     * @param bookingId
     * @return
     */
    @GetMapping("/get/{bookingId}")
    public ResponseEntity<Booking> getBookingById(@PathVariable("bookingId") BigInteger bookingId) {
        Booking booking = bookingService.viewBooking(bookingId);
        ResponseEntity<Booking> response = new ResponseEntity<>(booking, HttpStatus.OK);
        return response;
    }

    /***
     * fetch all bookings from database
     * @return Booking List as response
     */
    @GetMapping
    public ResponseEntity<List<Booking>> fetchAllBookings() {
        List<Booking> bookings = bookingService.viewAllBooking();
        ResponseEntity<List<Booking>> response = new ResponseEntity<>(bookings, HttpStatus.OK);
        return response;
    }

    /***
     * delete booking if exist
     * @param bookingId
     * @return response as true or false
     */

    /***
     * fetch all airports from airport service url
     * @return
     */
    @GetMapping("/airports")
    public ResponseEntity<AirportDetailsDto[]> fetchAllAirports(){
        String url = airportServiceBaseUrl;
        AirportDetailsDto[] airports  = restTemplate.getForObject(url,AirportDetailsDto[].class);
        ResponseEntity<AirportDetailsDto[]> response = new ResponseEntity<>(airports,HttpStatus.OK);
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

    /***
     * send acknowledge to flight Scheduler to cancel booking
     * @param booking
     */
    private void acknowledgeCancelBooking(Booking booking){
/*
        String url = flightScheduleServiceBaseUrl+"/cancelbooking";
        restTemplate.put(url,booking);
*/
    }

    /***
     * send req to Passenger service for cancel booking
     * @param passengerUINList
     */
    private void cancelRequestPassengerStore(List<BigInteger> passengerUINList){
/*
        String url = passengerServiceBaseUrl+"/remove";
        restTemplate.put(url,passengerUINList);
*/
    }

    /***
     * Handle Invalid Booking Id Exception
     * @param ex
     * @return
     */
    @ExceptionHandler(InvalidBookingIdException.class)
    public ResponseEntity<String>handleEmployeeNotFound(InvalidBookingIdException ex){
        Log.error("Invalid Booking Id exception",ex);
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;
    }

    /***
     * Handel Booking Not found Exception
     * @param ex
     * @return
     */
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String>handleEmployeeNotFound(BookingNotFoundException ex){
        Log.error("Booking not found exception",ex);
        String msg=ex.getMessage();
        ResponseEntity<String>response=new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        return response;
    }

    /**
     * this method will run when ConstraintViolationException occur
     * @param ex
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolate(ConstraintViolationException ex) {
        Log.error("constraint violation", ex);
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
        Log.error("Something went wrong", ex);
        String msg = ex.getMessage();
        ResponseEntity<String> response = new ResponseEntity<>(msg, HttpStatus.INTERNAL_SERVER_ERROR);
        return response;
    }
}
