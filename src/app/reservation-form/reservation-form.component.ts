import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ActivatedRoute } from '@angular/router';


@Component({
    selector: 'cow-reservation-form',
    templateUrl: './reservation-form.component.html',
    styleUrls: ['./reservation-form.component.css']
})
export class ReservationFormComponent implements OnInit {

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

    space: SpaceModel;
    spaceSubscription: Subscription;

    constructor(private spaceService: SpaceService, private route: ActivatedRoute) {
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

