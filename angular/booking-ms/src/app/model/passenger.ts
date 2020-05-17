export class Passenger {

    pnrNumber: Number;
    passengerUIN: Number;
    passengerName: String;
    passengerAge: Number;
    gender: String;
    luggage: Number;

    constructor(pnrNumber: Number, passengerUIN: Number, passengerName: String, passengerAge: Number, gender: String, luggage: Number) {
        this.pnrNumber = pnrNumber;
        this.passengerUIN = passengerUIN;
        this.passengerName = passengerName;
        this.passengerAge = passengerAge;
        this.gender = gender;
        this.luggage = luggage;
    }
}