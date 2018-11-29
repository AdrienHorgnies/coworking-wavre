import { Component, Input, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ImageService } from "../image.service";


@Component({
    selector: 'cow-reservation-form',
    templateUrl: './reservation-form.component.html',
    styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit {

    @Input() space: SpaceModel;

    equipementNumber: number = 1;
    equipementNumberUpdate: number = 1;
    date1: string = '25/02/2019';
    date2 = '';
    display: boolean = true;

    // todo: initialiser le nombre de personnes >1 et <capacity
    numberpers: string = 'number';

    total(montant: string): string {
        return montant;
    }


    spaces: Array<SpaceModel>;
    spacesSubscription: Subscription;

    constructor(private spaceService: SpaceService, public imageService: ImageService) {
    }

    ngOnInit() {
        this.spacesSubscription = this.spaceService.list().subscribe(spaces => this.spaces = spaces);
    }

    ngOnDestroy() {
        if (this.spacesSubscription) {
            this.spacesSubscription.unsubscribe();
        }
    }
}

