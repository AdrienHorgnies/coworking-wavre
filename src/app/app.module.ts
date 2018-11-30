import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";
import { Ng5SliderModule } from 'ng5-slider';
import { DateValueAccessorModule } from "angular-date-value-accessor";
import { PDFExportModule } from "@progress/kendo-angular-pdf-export";
import { GoTopButtonModule } from 'ng2-go-top-button';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

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
import { ProfileComponent } from './profile/profile.component';
import { TabModule } from 'angular-tabs-component';
import { FullCalendarModule } from "ng-fullcalendar";
import { CommonModule } from "@angular/common";
import { NgbModalModule } from "@ng-bootstrap/ng-bootstrap";
import { InvoiceComponent } from './invoice/invoice.component';


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
        ProfileComponent,
        InvoiceComponent,
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
        RouterModule.forRoot(ROUTES),
        Ng5SliderModule,
        DateValueAccessorModule,
        TabModule,
        PDFExportModule,
        FullCalendarModule,
        CommonModule,
        NgbModalModule,
        GoTopButtonModule,
        BrowserAnimationsModule
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useExisting: JwtInterceptorService, multi: true}
    ],
    bootstrap: [AppComponent]
})

export class AppModule {
}
