import { Component, OnInit } from '@angular/core';
import { SpaceModel} from "../models/space.model";
import { ActivatedRoute, Router } from "@angular/router";
import { SpaceService } from "../space.service";

@Component({
    selector: 'cow-spaces-list',
    templateUrl: './spaces-list.component.html',
    styleUrls: ['./spaces-list.component.css']
})

export class SpacesListComponent implements OnInit {

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
