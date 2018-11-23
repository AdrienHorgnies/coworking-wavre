import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SpacesComponent} from './spaces/spaces.component';
import { SpaceComponent } from './space/space.component';



import {FormsModule, NgModel} from '@angular/forms';
import {BookingmeetingComponent} from './bookingmeeting/bookingmeeting.component';

@NgModule({
    declarations: [
        AppComponent,
        SpacesComponent,
        SpaceComponent,
        BookingmeetingComponent
    ],
    imports: [
        BrowserModule,  FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
