import { Component, EventEmitter, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ImageService } from "../image.service";
import { LabelType, Options } from "ng5-slider";
import { debounceTime, filter, switchMap } from 'rxjs/operators';
import { CityService } from "../city.service";

@Component({
    selector: 'cow-spaces-list',
    templateUrl: './spaces-list.component.html',
    styleUrls: ['./spaces-list.component.css']
})
export class SpacesListComponent implements OnInit, OnDestroy {

    spaces: Array<SpaceModel>;

    subscriptions: Array<Subscription> = new Array<Subscription>();

    $queryEmitter = new EventEmitter<string>();

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
        },
        zipCode: {
            key: "building.city.zipCode.equals",
            value: null
        }
    };

    options: Options = {
        floor: this.filters.priceMin.value,
        ceil: this.filters.priceMax.value,
        step: 10,
        translate: (value: number, label: LabelType): string => {
            switch (label) {
                case LabelType.Low:
                    return '<b>Min price:</b> ' + value + ' €';
                case LabelType.High:
                    return '<b>Max price:</b> ' + value + ' €';
                default:
                    return value + ' €';
            }
        }
    };

    zipCodes: Array<number> = new Array<number>();

    constructor(private spaceService: SpaceService, public imageService: ImageService, public cityService: CityService) {
        // hack to correctly update slider options as Angular doesn't see the difference unless it's a different object
        setTimeout(() => {
            this.options = Object.assign({}, this.options);
        }, 500);
    }

    buildQuery(): string {
        return Object.values(this.filters)
            .filter(filter => filter.value != null)
            .filter(filter => filter.value != "null")
            .map(filter => `${filter.key}:${filter.value}`)
            .join(",");
    }


    onFilterFormChanges() {
        this.$queryEmitter.emit(this.buildQuery());
    }

    ngOnInit() {
        this.subscriptions.push(this.spaceService.list().subscribe(spaces => this.spaces = spaces));
        this.subscriptions.push(this.spaceService.minPrice().subscribe(value => {
            this.filters.priceMin.value = value;
            this.options.floor = value;
        }));
        this.subscriptions.push(this.spaceService.maxPrice().subscribe(value => {
            this.filters.priceMax.value = value;
            this.options.ceil = value;
        }));
        this.subscriptions.push(this.cityService.zipCodesWithSpaces().subscribe(value => this.zipCodes = value));
        this.subscriptions.push(this.$queryEmitter.pipe(
            filter(query => query.length > 0),
            debounceTime(400),
            switchMap(query => this.spaceService.search(query))
        ).subscribe(
            spaces => this.spaces = spaces
        ));
    }

    ngOnDestroy() {
        this.subscriptions.forEach(sub => {
            sub.unsubscribe();
        });
    }
}
