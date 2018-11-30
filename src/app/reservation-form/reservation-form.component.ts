import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ActivatedRoute } from '@angular/router';
import * as moment from 'moment';
import { EquipmentTypeModel } from "../models/equipmentType.model";
import { ServiceTypeModel } from "../models/serviceType.model";
import { ReservationService } from "../reservation.service";
import { EquipmentOrderModel } from "../models/equipmentOrder.model";
import { ServiceOrderModel } from "../models/serviceOrder.model";
import { InvoiceService } from "../invoice.service";


@Component({
    selector: 'cow-reservation-form',
    templateUrl: './reservation-form.component.html',
    styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit, OnDestroy {

    startDate: Date = moment().add(1, 'day').startOf('day').toDate();
    endDate: Date = moment().add(8, 'days').startOf('day').toDate();
    peopleNumber: number = 1;
    equipments = {};
    services = {};

    space: SpaceModel;
    spaceSubscription: Subscription;

    reservationSubscription: Subscription;

    constructor(private spaceService: SpaceService, private route: ActivatedRoute, private reservationService: ReservationService, public invoiceService: InvoiceService) {
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

    buildReservation() {
        return {
            // todo take title from input
            title: "TITLE",
            startDate: this.startDate,
            endDate: this.endDate,
            peopleNumber: this.peopleNumber,
            space: this.space,
            equipmentOrders: <Array<EquipmentOrderModel>>Object.values(this.equipments),
            serviceOrders: <Array<ServiceOrderModel>>Object.values(this.services)
        };
    }

    submitReservation() {
        this.reservationSubscription = this.reservationService.make(this.buildReservation()).subscribe(resp => console.log(resp));
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

