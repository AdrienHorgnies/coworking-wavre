import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {SpacesComponent} from './spaces/spaces.component';
import { SpaceComponent } from './space/space.component';

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {FormsModule, NgModel} from '@angular/forms';

@NgModule({
    declarations: [
        AppComponent,
        SpacesComponent,
        SpaceComponent
    ],
    imports: [
        BrowserModule, Ng2SearchPipeModule, FormsModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
