import { Component, OnDestroy, OnInit } from '@angular/core';
import { UserModel } from "./models/user.model";
import { Subscription } from "rxjs";
import { UserService } from "./user.service";

@Component({
    selector: 'cow-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

    user: UserModel;
    userEventsSubscription: Subscription;

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.userEventsSubscription = this.userService.$userEvents.subscribe(user => this.user = user);
    }

    ngOnDestroy() {
        if (this.userEventsSubscription) {
            this.userEventsSubscription.unsubscribe();
        }
    }
}
