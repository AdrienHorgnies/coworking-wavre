import { Component, OnInit } from '@angular/core';
import { UserService } from "../user.service";
import { Router } from "@angular/router";

@Component({
    selector: 'cow-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    credentials = {
        login: '',
        password: '',
        rememberMe: false
    };
    authenticationFailed = false;

    constructor(private userService: UserService, private router: Router) {
    }

    ngOnInit() {
    }

    authenticate() {
        this.userService.authenticate(this.credentials.login, this.credentials.password, this.credentials.rememberMe).subscribe(
            success => this.router.navigate(['/']),
            error => this.authenticationFailed = true
        );
    }

}
