import { Component, Input, OnInit } from '@angular/core';
import { SpaceModel } from "../models/space.model";

@Component({
    selector: 'cow-reservation-form',
    templateUrl: './reservation-form.component.html',
    styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit {

    @Input() space: SpaceModel;

    constructor() {
    }

    ngOnInit() {
    }

}
