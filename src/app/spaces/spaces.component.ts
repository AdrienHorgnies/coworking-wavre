import {Component, OnInit} from '@angular/core';
import {SpaceModel} from '../models/space.model';
import {SpaceService} from '../space.service';


import {ActivatedRoute, Router} from "@angular/router";


@Component({
    selector: 'cow-spaces',
    templateUrl: './spaces.component.html',
    styleUrls: ['./spaces.component.css']
})

export class SpacesComponent implements OnInit {

    spaces: Array<SpaceModel> = [];

    constructor(private spaceService: SpaceService, private route: ActivatedRoute, private router: Router) {
    }

    ngOnInit() {
        this.spaceService.list().subscribe(spaces => this.spaces = spaces);

    }

    onClick(spaceId: number){
        this.router.navigate(['spacedetails', spaceId])
    }

}
