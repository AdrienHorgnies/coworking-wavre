import { Component, OnInit } from '@angular/core';
import { UserService } from "../user.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
    selector: 'cow-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    returnUrl: string;

    credentials = {
        login: '',
        password: '',
        rememberMe: false
    };
    authenticationFailed = false;

    constructor(private userService: UserService, private router: Router, private route: ActivatedRoute) {
    }

    ngOnInit() {
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
        console.log(this.returnUrl);
    }

    authenticate() {
        this.userService.authenticate(this.credentials.login, this.credentials.password, this.credentials.rememberMe).subscribe(
            success => this.router.navigateByUrl(this.returnUrl),
            error => this.authenticationFailed = true
        );
    }

}
