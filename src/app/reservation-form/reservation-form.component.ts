import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ActivatedRoute } from '@angular/router';
import * as moment from 'moment';
import { EquipmentTypeModel } from "../models/equipmentType.model";
import { ServiceTypeModel } from "../models/serviceType.model";


@Component({
    selector: 'cow-reservation-form',
    templateUrl: './reservation-form.component.html',
    styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit, OnDestroy {

    startDate: Date = moment().startOf('day').toDate();
    endDate: Date = moment().add(7, 'days').toDate();
    peopleNumber: number = 1;
    equipments = {};
    services = {};

    space: SpaceModel;
    spaceSubscription: Subscription;

    constructor(private spaceService: SpaceService, private route: ActivatedRoute) {
        moment.locale("fr");
    }

    updateEquipmentOrder(equipmentType: EquipmentTypeModel, quantity: number) {
        if (quantity === 0 && this.equipments[equipmentType.name]) {
            delete this.equipments[equipmentType.name];
        } else if (quantity > 0) {
            this.equipments[equipmentType.name] = {
                quantity,
                equipmentType
            };
        }
    }

    updateServiceOrder(serviceType: ServiceTypeModel) {
        if (this.services[serviceType.name]) {
            delete this.services[serviceType.name];
        } else {
            this.services[serviceType.name] = {
                quantity: 1,
                serviceType
            }
        }
    }

    // kudo to https://gist.github.com/icemilo/a0b98a1508aab82853eb
    calculateBusinessDays(firstDate, secondDate) {
        let day1 = moment(firstDate).startOf('day');
        let day2 = moment(secondDate).startOf('day');
        let adjust = 1;

        if ((day1.dayOfYear() === day2.dayOfYear()) && (day1.year() === day2.year())) {
            return 0;
        }

        if (day2.isBefore(day1)) {
            const temp = day1;
            day1 = day2;
            day2 = temp;
        }

        //Check if first date starts on weekends
        if (day1.day() === 6) { //Saturday
            //Move date to next week monday
            day1.day(8);
        } else if (day1.day() === 0) { //Sunday
            //Move date to current week monday
            day1.day(1);
        }

        //Check if second date starts on weekends
        if (day2.day() === 6) { //Saturday
            //Move date to current week friday
            day2.day(5);
        } else if (day2.day() === 0) { //Sunday
            //Move date to previous week friday
            day2.day(-2);
        }

        const day1Week = day1.week();
        let day2Week = day2.week();

        //Check if two dates are in different week of the year
        if (day1Week !== day2Week) {
            //Check if second date's year is different from first date's year
            if (day2Week < day1Week) {
                day2Week += day1Week;
            }
            //Calculate adjust value to be substracted from difference between two dates
            adjust += -2 * (day2Week - day1Week);
        }

        return day2.diff(day1, 'days') + adjust;
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

