import { Component, OnInit } from '@angular/core';
import { Booking } from '../model/booking';
import { Passenger } from '../model/passenger';
import { Airport } from '../model/airport';
import { FormBuilder } from '@angular/forms';
import { ScheduleFligt } from '../model/scheduleFlight';

@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  bookings:Booking[];
  createBooking(bookingDetails:any){//dont know if booking details shoud be of type booking or else
    let src = bookingDetails.source;
    let destination = bookingDetails.destination;
    let bookingDate = bookingDetails.bookingDate;
    let userID = bookingDetails.userID;
    let passengers:Passenger[] = bookingDetails.passengers;
    let ticketcost = bookingDetails.ticketcost;
    let flightNumber = bookingDetails.flightNumber;
    let numberOfpassenger = bookingDetails.numberOfpassenger;
    let billingAddress = bookingDetails.billingAddress;

    let booking = new Booking(userID,src,destination,bookingDate,passengers,ticketcost,flightNumber,numberOfpassenger,billingAddress);
    this.bookings.push(booking);
  }

  airports = [
    new Airport("BOM","Mumbai International Airport","Mumbai"),
    new Airport("BHO","Bhopal Airport","Bhopal"),
    new Airport("BLR","Bangalore International Airport","Bangalore")
  ]

  flights = [
    new ScheduleFligt(1111,"Mumbai","Bangalore"),
    new ScheduleFligt(1122,"Pune","Mumbai"),
    new ScheduleFligt(1010,"Delhi","Mumbai")
  ]
  
  showSearchedFlights:ScheduleFligt[];

  viewFlights(source:String,destination:String,bookingDate:Date){
    this.flights.forEach(element => {
      if(element.source.toLowerCase().localeCompare(source.toLowerCase())){
        this.showSearchedFlights.push(element);
      }
    });
  }

  

  constructor() { }

  ngOnInit(): void {
    
  }

}
