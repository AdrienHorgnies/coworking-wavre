import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ImageService } from "../image.service";

@Component({
    selector: 'cow-spaces-list',
    templateUrl: './spaces-list.component.html',
    styleUrls: ['./spaces-list.component.css']
})
export class SpacesListComponent implements OnInit, OnDestroy {

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
