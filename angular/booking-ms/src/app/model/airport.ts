export class Airport {
    airportName: String;
    airportCode: String;
    airportLocation: String;

    constructor(airportCode: String, airportName: String, airportLocation: String) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.airportLocation = airportLocation;
    }
}