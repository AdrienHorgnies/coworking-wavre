import { Component, EventEmitter, OnDestroy, OnInit } from '@angular/core';
import { Observable, Subscription } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ImageService } from "../image.service";
import { LabelType } from "ng5-slider";
import { debounceTime, filter, switchMap, tap } from 'rxjs/operators';
import { CityService } from "../city.service";
import { startWith } from 'rxjs/internal/operators/startWith';

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
            value: null
        },
        priceMax: {
            key: 'price.max',
            value: null
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

    zipCodes: Observable<Array<number>>;
    minPrice: Observable<number>;
    maxPrice: Observable<number>;

    constructor(private spaceService: SpaceService, public imageService: ImageService, public cityService: CityService) {
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

    sliderText(value: number, label: LabelType): string {
        switch (label) {
            case LabelType.Low:
                return '<b>Min price:</b> ' + value + ' €';
            case LabelType.High:
                return '<b>Max price:</b> ' + value + ' €';
            default:
                return value + ' €';
        }
    }

    ngOnInit() {
        this.subscriptions.push(this.spaceService.list().subscribe(spaces => this.spaces = spaces));
        this.minPrice = this.spaceService.minPrice().pipe(
            tap(value => this.filters.priceMin.value = value),
            startWith(20)
        );
        this.maxPrice = this.spaceService.maxPrice().pipe(
            tap(value => this.filters.priceMax.value = value),
            startWith(2900)
        );
        this.zipCodes = this.cityService.zipCodesWithSpaces();
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
