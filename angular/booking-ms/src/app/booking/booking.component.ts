import { Component, OnInit, Output } from '@angular/core';
import { Booking } from '../model/booking';
import { Passenger } from '../model/passenger';
import { Airport } from '../model/airport';
import { FormBuilder } from '@angular/forms';
import { FligtDetails } from '../model/flightDetails';
import { BookingService } from '../services/bookingservice';
import { Observable } from 'rxjs';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-booking',
  templateUrl: './booking.component.html',
  styleUrls: ['./booking.component.css']
})
export class BookingComponent implements OnInit {

  constructor(service: BookingService) {
    this.service = service;
    let observable: Observable<Airport[]> = this.service.fetchAllAirports();
    observable.subscribe(
      airportList => {
        this.airports = airportList;
        console.log("inside booking success callback number of airport is =" + this.airports.length);
      },
      err => console.log("Error" + err)
    );
  }

  ngOnInit(): void {

  }


  service: BookingService;
  flights = [] = [];
  airports: Airport[] = [];

  srcAirport: string = undefined;
  destAirport: string = undefined;
  flagShowFlights: boolean = false;

  source: String;
  destination: String;
  bookingDate: String;
  BOOKINGDATE: Date;



  searchFlights(myForm: any) {

    let flightDetails = myForm.value;
    this.bookingDate = flightDetails.bookingDate;
    this.source = flightDetails.source;
    this.destination = flightDetails.destination;
    let stringDate: String = new DatePipe('en-US').transform(this.bookingDate, 'dd-MM-yyyy');
    console.log(stringDate);

    this.service.cacheBookingInfo(this.source, this.destination, "20-02-2021");

    let observable: Observable<FligtDetails[]> = this.service.fetchSearchedFlights(this.source, this.destination, stringDate);

    observable.subscribe(
      flightList => {
        this.flights = flightList;
        console.log("inside booking success callback number of flight is =" + this.flights.length);
      },
      err => console.log("Error " + err)
    );

    this.flagShowFlights = true;
  }

  onClick(flight: FligtDetails) {
    this.service.cacheFlightInfo(flight);
  }

}
