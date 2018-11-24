import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SpacesComponent} from './spaces/spaces.component';
import { SpaceComponent } from './space/space.component';



import {FormsModule} from '@angular/forms';
import {BookingmeetingComponent} from './bookingmeeting/bookingmeeting.component';
import { BookingspaceComponent } from './bookingspaceprivate/bookingspace.component';
import { BookingspaceopenComponent } from './bookingspaceopen/bookingspaceopen.component';
import { BookingspacebubbleComponent } from './bookingspacebubble/bookingspacebubble.component';

@NgModule({
    declarations: [
        AppComponent,
        SpacesComponent,
        SpaceComponent,
        BookingmeetingComponent,
        BookingspaceComponent,
        BookingspaceopenComponent,
        BookingspacebubbleComponent
    ],
    imports: [
        BrowserModule,  FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
