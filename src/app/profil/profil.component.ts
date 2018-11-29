import { Component, OnInit,ViewChild } from '@angular/core';
import { CalendarComponent } from "ng-fullcalendar";
import { Options } from 'fullcalendar';

@Component({
  selector: 'cow-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

    calendarOptions: Options;
    @ViewChild(CalendarComponent) ucCalendar: CalendarComponent;

  constructor() { }

  ngOnInit() {
      this.calendarOptions = {
          editable: true,
          eventLimit: false,
          header: {
              left: 'prev,next today',
              center: 'title',
              right: 'month,agendaWeek,agendaDay,listMonth'
          },

      };
  }

}
