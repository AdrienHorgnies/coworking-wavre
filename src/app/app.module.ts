import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SpacesComponent} from './spaces/spaces.component';
import { SpaceComponent } from './space/space.component';
import { TabsComponent } from './tabs/tabs.component';
import { BookingmeetingComponent } from './bookingmeeting/bookingmeeting.component';

@NgModule({
    declarations: [
        AppComponent,
        SpacesComponent,
        SpaceComponent,
        TabsComponent,
        BookingmeetingComponent
    ],
    imports: [
        BrowserModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
