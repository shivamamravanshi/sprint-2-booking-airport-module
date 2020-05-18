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

  ticketInfo: TicketInfo;
  passengers: Passenger[] = [];

  constructor(service: BookingService) {
    this.service = service;
  }

  ngOnInit(): void {
    this.service.getTicketInfo().subscribe(data=>{
      this.ticketInfo=data;
      this.passengers=data.passengerList;
    },
    err=>{
      console.log("Error"+err);
    });
  }

}
