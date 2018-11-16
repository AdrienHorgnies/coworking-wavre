import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {SearchComponent} from './search/search.component';
import {MeetingsComponent} from './meetings/meetings.component';
import {MeetingComponent} from './meeting/meeting.component';

@NgModule({
  declarations: [
    AppComponent,
    SearchComponent,
      MeetingsComponent,
      MeetingComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
