import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { SpacesComponent } from './spaces/spaces.component';
import { SpacesDetailComponent} from "./spaces-detail/spaces-detail.component";

import { Ng2SearchPipeModule } from 'ng2-search-filter';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from "@angular/router";

const appRoutes: Routes= [
    { path: 'space', component: SpacesComponent},
    { path: 'spacedetails/:id', component: SpacesDetailComponent },
]


@NgModule({
    declarations: [
        AppComponent,
        SpacesComponent,
        SpacesDetailComponent,

    ],
    imports: [
        BrowserModule, Ng2SearchPipeModule, FormsModule, RouterModule.forRoot(appRoutes)
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
