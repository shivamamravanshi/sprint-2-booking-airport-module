import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {FormsModule} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BookingComponent } from './booking/booking.component';
import { GenerateTicketComponent } from './generate-ticket/generate-ticket.component';
import { BookingService } from './services/bookingservice';
import { AddPassengerComponent } from './add-passenger/add-passenger.component';

@NgModule({
  declarations: [
    AppComponent,
    BookingComponent,
    GenerateTicketComponent,
    AddPassengerComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [BookingService],
  bootstrap: [AppComponent]
})
export class AppModule { }
