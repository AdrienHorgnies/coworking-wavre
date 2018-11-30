import { Injectable } from '@angular/core';
import * as moment from 'moment';
import { ReservationModel } from "./models/reservation.model";
import { OrderedEquipmentModel } from "./models/orderedEquipment.model";
import { OrderedServiceModel } from "./models/orderedService.model";
import { MakeReservationModel } from "./models/makeReservationModel";

@Injectable({
    providedIn: 'root'
})
export class InvoiceService {

    constructor() {
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

    invoicePDFName(reservation: ReservationModel) {
        return `invoice-${reservation.id}-${reservation.user.lastName}-${moment(reservation.orderDate).format("YYYY-MM-DD")}.pdf`;
    }

    equipmentOrderPricePerDayHTVA(equipmentOrder: OrderedEquipmentModel) {
        return equipmentOrder.quantity * equipmentOrder.unitPricePerDay;
    }

    serviceOrderPricePerDayHTVA(reservation: ReservationModel, serviceOrder: OrderedServiceModel) {
        return reservation.peopleNumber * serviceOrder.unitPricePerDay;
    }

    equipmentOrderPeriodPriceTVAC(reservation: ReservationModel, equipmentOrder: OrderedEquipmentModel) {
        return this.equipmentOrderPricePerDayHTVA(equipmentOrder) * 1.21 * this.calculateBusinessDays(reservation.startDate, reservation.endDate);
    }

    serviceOrderPeriodPriceTVAC(reservation: ReservationModel, serviceOrder: OrderedServiceModel) {
        return this.serviceOrderPricePerDayHTVA(reservation, serviceOrder) * 1.21 * this.calculateBusinessDays(reservation.startDate, reservation.endDate);
    }

    spaceLocationPriceTVAC(reservation: ReservationModel) {
        return reservation.spacePricePerDay * 1.21 * this.calculateBusinessDays(reservation.startDate, reservation.endDate);
    }

    totalPricePerDayHTVA(reservation: ReservationModel) {
        const equipmentsPriceTVAC = reservation.equipmentOrders
            .map(orderedEquipment => this.equipmentOrderPricePerDayHTVA(orderedEquipment))
            .reduce((a, b) => a + b, 0);
        const servicesPriceTVAC = reservation.serviceOrders
            .map(orderedService => this.serviceOrderPricePerDayHTVA(reservation, orderedService))
            .reduce((a, b) => a + b, 0);

        return reservation.spacePricePerDay + equipmentsPriceTVAC + servicesPriceTVAC;
    }

    grandTotalPriceTVAC(reservation: ReservationModel) {
        const businessDays = this.calculateBusinessDays(reservation.startDate, reservation.endDate);
        const locationPriceTVAC = businessDays * reservation.spacePricePerDay * 1.21;
        const equipmentsPriceTVAC = reservation.equipmentOrders
            .map(orderedEquipment => this.equipmentOrderPeriodPriceTVAC(reservation, orderedEquipment))
            .reduce((a, b) => a + b, 0);
        const servicesPriceTVAC = reservation.serviceOrders
            .map(orderedService => this.serviceOrderPeriodPriceTVAC(reservation, orderedService))
            .reduce((a, b) => a + b, 0);

        return locationPriceTVAC + equipmentsPriceTVAC + servicesPriceTVAC;
    }

    quotationTotalPriceHTVA(reservation: MakeReservationModel) {
        const businessDays = this.calculateBusinessDays(reservation.startDate, reservation.endDate);
        const locationPriceHTVA = businessDays * reservation.space.price;
        const servicePriceHTVA = reservation.serviceOrders
            .map(serviceOrder => reservation.peopleNumber * businessDays * serviceOrder.serviceType.price)
            .reduce((a, b) => a + b, 0);
        const equipmentPriceHTVA = reservation.equipmentOrders
            .map(equipmentOrder => equipmentOrder.quantity * businessDays * equipmentOrder.equipmentType.price)
            .reduce((a, b) => a + b, 0);

        return locationPriceHTVA + servicePriceHTVA + equipmentPriceHTVA;
    }
}
