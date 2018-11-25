import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { RouterModule} from "@angular/router";
import { Routes } from "@angular/router";
import { Ng2SearchPipeModule } from 'ng2-search-filter';

import { ROUTES } from "./app.routes";
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { JwtInterceptorService } from "./jwt-interceptor.service";
import { LoginComponent } from "./login/login.component";
import { MenuComponent } from './menu/menu.component';
import { HomeComponent } from './home/home.component';
import {SpacesDetailComponent} from "./spaces-detail/spaces-detail.component";
import {SpacesListComponent} from "./spaces-list/spaces-list.component";

const appRoutes: Routes= [
    { path: 'space', component: SpacesListComponent},
    { path: 'spacedetails/:id', component: SpacesDetailComponent },
]

@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
        LoginComponent,
        MenuComponent,
        SpacesListComponent,
        HomeComponent,
        SpacesDetailComponent
    ],
    imports: [
        BrowserModule,
        HttpClientModule,
        ReactiveFormsModule,
        FormsModule,
        RouterModule.forRoot(ROUTES),
        RouterModule.forRoot(appRoutes)
    ],
    providers: [
        {provide: HTTP_INTERCEPTORS, useExisting: JwtInterceptorService, multi: true}
    ],
    bootstrap: [AppComponent]
})

export class AppModule {
}
