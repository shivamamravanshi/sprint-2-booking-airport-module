import { Passenger } from './passenger';

export class Booking{
    userId:Number;
    source:String;
    destination:String;
    bookingDate:String;
    passengerList:Passenger[];
    ticketCost:Number;
    flightNumber:Number;
    arrivalTime:String;
    departureTime:String;
    numberOfPassengers:Number;
    billingAddress:String;
    contactNumber:Number

    constructor(userId:Number,source:String,destination:String,arrivalTime:String,departureTime:String,bookingDate:String,passengers:Passenger[],ticketCost:Number,
        flightNumber:Number,numberOfPassengers:Number,billingAddress:String,contactNumber:Number){
            this.userId=userId;
            this.source=source;
            this.destination=destination;
            this.arrivalTime=arrivalTime;
            this.departureTime=departureTime;
            this.bookingDate=bookingDate;
            this.passengerList=passengers;
            this.ticketCost=ticketCost;
            this.flightNumber=flightNumber;
            this.numberOfPassengers=numberOfPassengers;
            this.billingAddress=billingAddress;
            this.contactNumber=contactNumber;
    }
}