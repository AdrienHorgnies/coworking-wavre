import { Component, OnInit } from '@angular/core';
import {MeetingModel} from '../models/meeting.model';
import {MeetingService} from '../meeting.service';


@Component({
  selector: 'cow-meetings',
  templateUrl: './meetings.component.html',
  styleUrls: ['./meetings.component.css']
})
export class MeetingsComponent implements OnInit {

    meetings: Array<MeetingModel> = [];

    constructor(private meetingService: MeetingService) {
    }

    ngOnInit() {
        this.meetingService.list().subscribe(meetings => this.meetings = meetings);
    }


}
