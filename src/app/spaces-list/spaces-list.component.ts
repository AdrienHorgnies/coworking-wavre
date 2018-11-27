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

    searchSubscription: Subscription;
    subscriptions: Array<Subscription> = new Array<Subscription>();

    filters = {
        priceMin: {
            key: 'price.min',
            value: 0
        },
        priceMax: {
            key: 'price.max',
            value: 2000
        },
        type: {
            key: 'type.equals',
            value: null
        }
    };

    constructor(private spaceService: SpaceService, public imageService: ImageService) {
    }

    buildQuery(): string {
        return Object.values(this.filters)
            .filter(filter => filter.value != null)
            .filter(filter => filter.value != "null")
            .map(filter => `${filter.key}:${filter.value}`)
            .join(",");
    }


    onFilterFormChanges() {
        this.ngOnDestroy();

        const query = this.buildQuery();

        if (query) {
            console.log(this.buildQuery());
            this.searchSubscription = this.spaceService.search(query).subscribe(spaces => this.spaces = spaces);
        } else {
            this.spacesSubscription = this.spaceService.list().subscribe(spaces => this.spaces = spaces);
        }
    }

    ngOnInit() {
        this.subscriptions.push(this.spaceService.list().subscribe(spaces => this.spaces = spaces));
        this.subscriptions.push(this.spaceService.minPrice().subscribe(value => this.filters.priceMin.value = value));
        this.subscriptions.push(this.spaceService.maxPrice().subscribe(value => this.filters.priceMax.value = value));
    }

    ngOnDestroy() {
        this.subscriptions.forEach(sub => {
            sub.unsubscribe();
        });
        this.subscriptions = new Array<Subscription>();
    }
}
