// import {PipeTransform, Pipe} from '@angular/core';
// import {MeetingModel} from './models/meeting.model';
//
// @Pipe({
//     name: 'meetingFilter',
//     pure: false
// })
//
// export class MeetingFilterPipe implements PipeTransform {
//     private counter = 0;
// transform(meetings: MeetingModel[], SearchTerm: string): MeetingModel[] {
// this.counter++;
// if (!meetings || !SearchTerm) {
// return meetings;
// }
//
// return meetings.filter(meetingModel =>
// meetingModel.name.toLowerCase().indexOf(SearchTerm.toLowerCase()) !== -1);
// }
// }
