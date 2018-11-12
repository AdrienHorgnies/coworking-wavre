import {Component, OnInit} from '@angular/core';
import {SpaceModel} from '../models/space.model';
import {SpaceService} from '../space.service';

@Component({
    selector: 'cow-spaces',
    templateUrl: './spaces.component.html',
    styleUrls: ['./spaces.component.css']
})

export class SpacesComponent implements OnInit {

    spaces: Array<SpaceModel> = [];

    constructor(private spaceService: SpaceService) {
    }

    ngOnInit() {
        this.spaceService.list().subscribe(spaces => this.spaces = spaces);
    }

}
