import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ReservationModel } from "../models/reservation.model";
import { Subscription } from 'rxjs';
import { ReservationService } from "../reservation.service";
import * as moment from 'moment';

@Component({
    selector: 'cow-invoice',
    templateUrl: './invoice.component.html',
    styleUrls: ['./invoice.component.css']
})
export class InvoiceComponent implements OnInit {

    reservation: ReservationModel;
    reservationSubscription: Subscription;

    constructor(private route: ActivatedRoute, private reservationService: ReservationService) {
        moment.locale('fr');
    }

    displayDate(date: Date) {
        return moment(date).format('Do MMMM YYYY');
    }

    ngOnInit() {
        this.reservationSubscription = this.reservationService.get(+this.route.snapshot.paramMap.get('id')).subscribe(reservation => this.reservation = reservation);
    }

}
