import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ActivatedRoute } from '@angular/router';
import * as moment from 'moment';


@Component({
    selector: 'cow-reservation-form',
    templateUrl: './reservation-form.component.html',
    styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit, OnDestroy {

    moment = moment;

    startDate: Date = moment().toDate();
    endDate: Date = moment().add(7, 'days').toDate();
    peopleNumber: number = 1;

    equipementNumber: number = 1;
    equipementNumberUpdate: number = 1;
    display: boolean = true;

    space: SpaceModel;
    spaceSubscription: Subscription;

    constructor(private spaceService: SpaceService, private route: ActivatedRoute) {
        moment.locale("fr");
    }

    displayDate(date: Date) {
        return moment(date).format("dddd, MMMM Do YYYY")
    }

    ngOnInit() {
        this.spaceSubscription = this.spaceService.get(+this.route.snapshot.paramMap.get('id')).subscribe(space => this.space = space);
    }

    ngOnDestroy() {
        if (this.spaceSubscription) {
            this.spaceSubscription.unsubscribe();
        }
    }
}

