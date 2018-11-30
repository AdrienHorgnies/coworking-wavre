import { Component, OnDestroy, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { UserService } from "../user.service";
import { UserModel } from "../models/user.model";
import { Subscription } from 'rxjs';
import { ReservationService } from "../reservation.service";
import { ReservationModel } from "../models/reservation.model";
import * as moment from 'moment';

@Component({
    selector: 'cow-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit, OnDestroy {
    @ViewChild('modalContent')
    modalContent: TemplateRef<any>;

    user: UserModel;
    userSubscription: Subscription;

    reservations: Array<ReservationModel>;
    reservationsSubscription: Subscription;

    constructor(private userService: UserService, private reservationService: ReservationService, private modal: NgbModal) {
        moment.locale('fr')
    }

    displayDate(date: Date) {
        return moment(date).format("DD-MM-YYYY");
    }

    ngOnInit() {
        this.userSubscription = this.userService.getCurrentlyLoggedInUser().subscribe(
            user => this.user = user
        );
        this.reservationsSubscription = this.reservationService.listOwn().subscribe(reservations => this.reservations = reservations);
    }

    ngOnDestroy() {
        if (this.userSubscription) {
            this.userSubscription.unsubscribe();
        }
        if (this.reservationsSubscription) {
            this.reservationsSubscription.unsubscribe();
        }
    }
}
