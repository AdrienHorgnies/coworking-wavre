import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from "rxjs";
import { UserService } from "./user.service";

@Component({
    selector: 'cow-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, OnDestroy {

    isUserLoggedIn: boolean;
    isUserLoggedInSubscription: Subscription;

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.isUserLoggedInSubscription = this.userService.$isUserLoggedIn.subscribe(isUserLoggedIn => this.isUserLoggedIn = isUserLoggedIn);
    }

    ngOnDestroy() {
        if (this.isUserLoggedInSubscription) {
            this.isUserLoggedInSubscription.unsubscribe();
        }
    }
}
