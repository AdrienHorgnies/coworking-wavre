import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RouterModule } from '@angular/router';
import  { PDFExportModule } from "@progress/kendo-angular-pdf-export";
import { ROUTES } from './app.routes';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { JwtInterceptorService } from './jwt-interceptor.service';
import { LoginComponent } from './login/login.component';
import { MenuComponent } from './menu/menu.component';
import { SpacesListComponent } from './spaces-list/spaces-list.component';
import { HomeComponent } from './home/home.component';
import { SpaceDetailComponent } from './space-detail/space-detail.component';
import { ReservationFormComponent } from './reservation-form/reservation-form.component';
import { ProfilComponent } from './profil/profil.component';
import {TabModule} from 'angular-tabs-component';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';
import { FullCalendarModule} from "ng-fullcalendar";
import {CommonModule} from "@angular/common";
import {NgbModalModule} from "@ng-bootstrap/ng-bootstrap";
import {CalendarModule, DateAdapter} from "angular-calendar";
import {FlatpickrModule} from "angularx-flatpickr";


@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
        LoginComponent,
        MenuComponent,
        SpacesListComponent,
        HomeComponent,
        SpaceDetailComponent,
        ReservationFormComponent,
        ProfilComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
        TabModule,
        RouterModule.forRoot(ROUTES),
        PDFExportModule,
        FullCalendarModule,
        CommonModule,
        NgbModalModule,
        FlatpickrModule.forRoot(),
        CalendarModule.forRoot({
            provide: DateAdapter,
            useFactory: adapterFactory
        })
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useExisting: JwtInterceptorService, multi: true}
    ],
    bootstrap: [AppComponent]
})

export class AppModule {
}
