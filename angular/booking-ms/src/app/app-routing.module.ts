import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BookingComponent } from './booking/booking.component';
import { AddPassengerComponent } from './add-passenger/add-passenger.component';
import { GenerateTicketComponent } from './generate-ticket/generate-ticket.component';


const routes: Routes = [
  {
    path:'booking',
    component:BookingComponent
  },
  {
    path:'add-passenger',
    component:AddPassengerComponent
  },
  {
    path:'generate-ticket',
    component:GenerateTicketComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
