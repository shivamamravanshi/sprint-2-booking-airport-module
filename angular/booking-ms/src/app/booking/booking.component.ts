import { Component, OnInit } from '@angular/core';
import { Booking } from '../model/booking';
import { Passenger } from '../model/passenger';
import { Airport } from '../model/airport';
import { FormBuilder } from '@angular/forms';
import { FligtDetails } from '../model/flightDetails';


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  bookings:Booking[]=[];

    //userID will come from service after login, let's fake it
  getLoginUserId(){
    return 10; 
  }

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
    let booking = new Booking(userID,src,destination,bookingDate,passengers,ticketcost,flightNumber,numberOfpassenger,billingAddress);
    this.bookings.push(booking);
    this.flagShowFlights=true;
  }

  airports = [
    new Airport("BOM","Mumbai International Airport","Mumbai"),
    new Airport("BHO","Bhopal Airport","Bhopal"),
    new Airport("BLR","Bangalore International Airport","Bangalore")
  ]

  srcAirport:string=undefined;
  destAirport:string=undefined;
  
  flagShowFlights:boolean=false;  

  flights = [
    new FligtDetails(1111,"AirIndia","AirBus101","20:30","21:30",30000),
    new FligtDetails(1100,"AirIndia","AirBus101","19:30","20:30",31000),
    new FligtDetails(2211,"AirIndia","AirBus101","00:30","01:30",20000)
  ]

  

  constructor() { }

  ngOnInit(): void {
    
  }

}
