import { Component, OnInit } from '@angular/core';

@Component({
    selector: 'cow-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

    credentials = {
        login: '',
        password: ''
    };
    authenticationFailed = false;

    constructor() {
    }

    ngOnInit() {
    }

    authenticate() {
    }

}
