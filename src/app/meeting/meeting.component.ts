import { Component, Input, OnInit } from '@angular/core';
import { MeetingModel } from '../models/meeting.model';

@Component({
  selector: 'cow-meeting',
  templateUrl: './meeting.component.html',
  styleUrls: ['./meeting.component.css']
})
export class MeetingComponent implements OnInit {

    @Input() meetingModel: MeetingModel;

    constructor() { }

    ngOnInit() {
    }

}
