import { Component, OnDestroy, OnInit } from '@angular/core';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { ImageService } from "../image.service";

@Component({
    selector: 'cow-space-detail',
    templateUrl: './space-detail.component.html',
    styleUrls: ['./space-detail.component.css']
})
export class SpaceDetailComponent implements OnInit, OnDestroy {

    space: SpaceModel;
    spaceSubscription: Subscription;
    displayReservationForm: boolean;

    constructor(private route: ActivatedRoute, private spaceService: SpaceService, public imageService: ImageService) {
    }

    ngOnInit() {
        this.spaceSubscription = this.spaceService.get(+this.route.snapshot.paramMap.get('id')).subscribe(space => this.space = space);
    }

    ngOnDestroy() {
        this.spaceSubscription.unsubscribe();
    }

}
