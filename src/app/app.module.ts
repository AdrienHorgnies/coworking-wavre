import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { RouterModule } from "@angular/router";

import { ROUTES } from "./app.routes";
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { JwtInterceptorService } from "./jwt-interceptor.service";


@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent
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
