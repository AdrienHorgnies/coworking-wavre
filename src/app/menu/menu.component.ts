import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { UserService } from "../user.service";
import { Router } from '@angular/router';

@Component({
    selector: 'cow-menu',
    templateUrl: './menu.component.html',
    styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit, OnDestroy {

    navbarCollapsed = true;

    isUserLoggedIn: boolean;
    isUserLoggedInSubscription: Subscription;

    constructor(private userService: UserService, private router: Router) {
    }

    ngOnInit() {
        this.isUserLoggedInSubscription = this.userService.$isUserLoggedIn.subscribe(isUserLoggedIn => this.isUserLoggedIn = isUserLoggedIn);
    }

    ngOnDestroy() {
        if (this.isUserLoggedInSubscription) {
            this.isUserLoggedInSubscription.unsubscribe();
        }
    }

    toggleNavbar() {
        this.navbarCollapsed = !this.navbarCollapsed;
    }

    logout(event: Event) {
        event.preventDefault();
        this.userService.logout();
        this.router.navigate(['/']);
    }

}
