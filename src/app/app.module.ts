import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SpacesComponent} from './spaces/spaces.component';
import { SpaceComponent } from './space/space.component';



import {FormsModule, NgModel} from '@angular/forms';
import {BookingmeetingComponent} from './bookingmeeting/bookingmeeting.component';
import { BookingspaceComponent } from './bookingspace/bookingspace.component';

@NgModule({
    declarations: [
        AppComponent,
        SpacesComponent,
        SpaceComponent,
        BookingmeetingComponent,
        BookingspaceComponent
    ],
    imports: [
        BrowserModule,  FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
