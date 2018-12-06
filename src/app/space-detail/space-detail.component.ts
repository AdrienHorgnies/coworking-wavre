import { Component, OnDestroy, OnInit } from '@angular/core';
import { SpaceService } from '../space.service';
import { SpaceModel } from '../models/space.model';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { ImageService } from '../image.service';

@Component({
    selector: 'cow-space-detail',
    templateUrl: './space-detail.component.html',
    styleUrls: ['./space-detail.component.css']
})
export class SpaceDetailComponent implements OnInit, OnDestroy {

    isUserLoggedIn: boolean;
    isUserLoggedInSubscription: Subscription;

    space: SpaceModel;
    spaceSubscription: Subscription;

    constructor(private route: ActivatedRoute, private spaceService: SpaceService, private userService: UserService, public imageService: ImageService) {
    }

    ngOnInit() {
        this.spaceSubscription = this.spaceService.get(+this.route.snapshot.paramMap.get('id')).subscribe(space => this.space = <SpaceModel>space);
this.isUserLoggedInSubscription = this.userService.$isUserLoggedIn.subscribe(isUserLoggedIn => this.isUserLoggedIn = isUserLoggedIn);
    }

    ngOnDestroy() {
        if (this.spaceSubscription) {
            this.spaceSubscription.unsubscribe();
        }
        if (this.isUserLoggedInSubscription) {
            this.isUserLoggedInSubscription.unsubscribe();
        }
    }

}
