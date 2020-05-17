export class FligtDetails {
    flightNumber: Number;
    carrierName: String;
    flightModel: String;
    departureTime: String;
    arrivalTime: String;
    seatCost: Number;

    constructor(flightNumber: Number, carrierName: String, flightModel: String, departureTime: String, arrivalTime: String, seatCost: Number) {
        this.flightNumber = flightNumber;
        this.carrierName = carrierName;
        this.flightModel = flightModel;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.seatCost = seatCost;
    }
}