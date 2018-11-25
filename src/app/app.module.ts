import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";

import { ROUTES } from "./app.routes";
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { JwtInterceptorService } from "./jwt-interceptor.service";
import { LoginComponent } from "./login/login.component";
import { MenuComponent } from './menu/menu.component';
import { SpacesListComponent } from './spaces-list/spaces-list.component';
import { HomeComponent } from './home/home.component';
import { SpaceDetailComponent } from './space-detail/space-detail.component';
import { ReservationFormComponent } from './reservation-form/reservation-form.component';


@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
        LoginComponent,
        MenuComponent,
        SpacesListComponent,
        HomeComponent,
        SpaceDetailComponent,
        ReservationFormComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
        RouterModule.forRoot(ROUTES)
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useExisting: JwtInterceptorService, multi: true}
    ],
    bootstrap: [AppComponent]
})

export class AppModule {
}
