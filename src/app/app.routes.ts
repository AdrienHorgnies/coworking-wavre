import { Routes } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from "./login/login.component";
import { HomeComponent } from "./home/home.component";
import { SpacesListComponent } from "./spaces-list/spaces-list.component";
import { SpaceDetailComponent } from "./space-detail/space-detail.component";
import { ReservationFormComponent } from "./reservation-form/reservation-form.component";
import { CalendarComponent } from "./calendar/calendar.component";
import { ProfilComponent } from "./profil/profil.component";
import { FactureComponent } from "./facture/facture.component";

export const ROUTES: Routes = [
    {path: 'home', component: HomeComponent},
    {path: '', redirectTo: 'home', pathMatch: 'full'},
    {path: 'register', component: RegisterComponent},
    {path: 'login', component: LoginComponent},
    {path: 'spaces', component: SpacesListComponent},
    {path: 'spaces/:id', component: SpaceDetailComponent},
    {path: 'spaces/:id/reservation', component: ReservationFormComponent},
    {path: 'calendar', component: CalendarComponent},
    {path: 'user', component: ProfilComponent},
    {path: 'facture', component: FactureComponent}
];
