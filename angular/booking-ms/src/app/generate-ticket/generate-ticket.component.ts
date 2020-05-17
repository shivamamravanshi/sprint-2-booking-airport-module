import { Component, OnInit } from '@angular/core';
import { BookingService } from '../services/bookingservice';
import { TicketInfo } from '../model/ticketInfo';
import { Passenger } from '../model/passenger';

@Component({
  selector: 'app-generate-ticket',
  templateUrl: './generate-ticket.component.html',
  styleUrls: ['./generate-ticket.component.css']
})
export class GenerateTicketComponent implements OnInit {
  service: BookingService;

  ticketInfo: TicketInfo = null;
  passengers: Passenger[] = [];

  constructor(service: BookingService) {
    this.service = service
    this.generateTicket();
  }

  ngOnInit(): void {

  }

  generateTicket() {
    // let ticketInfo = this.service.getTicketInfo();
    // this.ticketInfo=ticketInfo;
    // this.passengers=ticketInfo.passengerList;
    let flightNumber: Number = 1212;
    let source: String = "Mumbai";
    let destination: String = "Delhi";
    let arrivalTime: String = "20:30";
    let departureTime = "21:30";
    let bookingDate = "20-09-2020";
    let userID = 10;
    let ticketCost = 7000;

    let passenger1 = new Passenger(1234567890, 123412341234, "Shivam", 22, "Male", 10);
    let passenger2 = new Passenger(1234567890, 123412341234, "Shubham", 26, "Male", 10);
    let passenger3 = new Passenger(1234567890, 123412341234, "Shivam Amravanshi", 22, "Male", 10);
    let passenger4 = new Passenger(1234567890, 123412341234, "Shubham Amravanshi", 22, "Male", 10);


    let passengerList: Passenger[] = [];

    passengerList.push(passenger1);
    passengerList.push(passenger2);
    passengerList.push(passenger3);
    passengerList.push(passenger4);
    let numberOfPassenger = 1;

    let bookingId = 1;
    let billingAddress = "Jabalpur";
    let contactNumber = 9876543234;

    this.ticketInfo = new TicketInfo(bookingId, userID, source, destination, arrivalTime, departureTime, bookingDate,
      passengerList, ticketCost, flightNumber, numberOfPassenger, billingAddress, contactNumber);
    this.passengers = this.ticketInfo.passengerList;
    let passenger: Passenger = this.passengers.pop();

  }



}
