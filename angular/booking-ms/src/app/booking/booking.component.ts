import { Component, OnInit } from '@angular/core';
import { Booking } from '../model/booking';
import { Passenger } from '../model/passenger';
import { Airport } from '../model/airport';
import { FormBuilder } from '@angular/forms';
import { FligtDetails } from '../model/flightDetails';
import { BookingService } from '../services/bookingservice';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  service:BookingService;

    //bookings:Booking[]=[];
    //userID will come from service after login, let's fake it
  getLoginUserId(){
    return 10; 
  }
  
  addedBooking:Booking=null;
/*
  createBooking(myForm:any){
    let bookingDetails=myForm.value;//here just fetching value of formgroup
    let src = bookingDetails.source;
    let destination = bookingDetails.destination;
    let bookingDate = bookingDetails.bookingDate;
    let userID = this.getLoginUserId();
    let passengers:Passenger[] = bookingDetails.passengers;
    let ticketcost = bookingDetails.ticketcost;
    let flightNumber = bookingDetails.flightNumber;
    let numberOfpassenger = bookingDetails.numberOfpassenger;
    let billingAddress = bookingDetails.billingAddress;
    this.addedBooking = new Booking(userID,src,destination,bookingDate,passengers,ticketcost,flightNumber,numberOfpassenger,billingAddress);
    
    let result = this.service.createBooking(this.addedBooking);
    result.subscribe((booking:Booking)=>{
        this.addedBooking=booking;
    },
    err=>{
      console.log("Error"+err);
    });
    myForm.reset();
    this.flagShowFlights=true;
    //this.bookings.push(booking);
    this.flagShowFlights=true;
  }
*/
  searchFlights(myForm:any){
    let flightDetails = myForm.value;
    let src = flightDetails.source;
    let destination = flightDetails.destination;
    let bookingDate = flightDetails.bookingDate;

    let observable:Observable<FligtDetails[]> = this.service.fetchSearchedFlights(src,destination,bookingDate);
    
    observable.subscribe(
      flightList=>{
        this.flights=flightList;
        console.log("inside success callback ="+this.flights.length);
      },
      err=>console.log("Error "+err)
    );
    
    this.flagShowFlights=true;
  }

  airports:Airport[] = [];

  srcAirport:string=undefined;
  destAirport:string=undefined;
  
  flagShowFlights:boolean=false;  

  flights = [] = [];

  constructor(service:BookingService) {
      this.service=service;
      let observable:Observable<Airport[]> = this.service.fetchAllAirports();
      observable.subscribe(
        airportList=>{
          this.airports=airportList;
         console.log("inside success callback ="+this.airports.length);
        },
        err=>console.log("Error"+err)
        );
  }

  ngOnInit(): void {
    
  }

}
