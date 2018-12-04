import { Component, EventEmitter, OnInit } from '@angular/core';
import { concat, Observable } from 'rxjs';
import { SpaceService } from "../space.service";
import { SpaceModel } from "../models/space.model";
import { ImageService } from "../image.service";
import { LabelType } from "ng5-slider";
import { debounceTime, filter, switchMap, tap } from 'rxjs/operators';
import { CityService } from "../city.service";
import { startWith } from 'rxjs/internal/operators/startWith';
import { BuildingModel } from "../models/building.model";
import { BuildingService } from "../building.service";

@Component({
    selector: 'cow-spaces-list',
    templateUrl: './spaces-list.component.html',
    styleUrls: ['./spaces-list.component.css']
})
export class SpacesListComponent implements OnInit {

    spaces: Observable<Array<SpaceModel>>;

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
        },
        building: {
            key: "building.id.equals",
            value: null
        }
    };

    zipCodes: Observable<Array<number>>;
    minPrice: Observable<number>;
    maxPrice: Observable<number>;
    buildings: Observable<Array<BuildingModel>>;

    constructor(private spaceService: SpaceService, public imageService: ImageService, public cityService: CityService, private buildingService: BuildingService) {
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
        const search = this.$queryEmitter.pipe(
            filter(query => query.length > 0),
            debounceTime(400),
            switchMap(query => this.spaceService.search(query))
        );
        this.spaces = concat(this.spaceService.list(),
            search);

        this.minPrice = this.spaceService.minPrice().pipe(
            tap(value => this.filters.priceMin.value = value),
            startWith(20)
        );
        this.maxPrice = this.spaceService.maxPrice().pipe(
            tap(value => this.filters.priceMax.value = value),
            startWith(2900)
        );
        this.zipCodes = this.cityService.zipCodesWithSpaces();
        this.buildings = this.buildingService.buildingsWithSpaces();
    }
}
