import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from "@angular/router";
import { UserService } from "../user.service";

@Component({
    selector: 'cow-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

    registrationFailed: boolean;
    loginCtrl: FormControl;
    passwordCtrl: FormControl;
    passwordTestCtrl: FormControl;
    firstNameCtrl: FormControl;
    lastNameCtrl: FormControl;
    rememberMeCtrl: FormControl;
    userForm: FormGroup;
    passwordForm: FormGroup;

    constructor(private fb: FormBuilder, private userService: UserService, private router: Router) {
    }

    static passwordMatch(control: FormGroup) {
        const password = control.get('password').value;
        const passwordTest = control.get('passwordTest').value;
        return password !== passwordTest ? {matchingError: true} : null;
    }

    ngOnInit() {
        this.loginCtrl = this.fb.control('', Validators.required);
        this.firstNameCtrl = this.fb.control('', Validators.required);
        this.lastNameCtrl = this.fb.control('', Validators.required);
        this.passwordCtrl = this.fb.control('', Validators.required);
        this.passwordTestCtrl = this.fb.control('', Validators.required);
        this.passwordForm = this.fb.group
        (
            {
                password: this.passwordCtrl,
                passwordTest: this.passwordTestCtrl
            },
            {
                validator: RegisterComponent.passwordMatch
            }
        );

        this.userForm = this.fb.group
        (
            {
                login: this.loginCtrl,
                passwordForm: this.passwordForm,
                firstName: this.firstNameCtrl,
                lastName: this.lastNameCtrl,
                rememberMe: this.rememberMeCtrl
            });
    }

    register() {
        this.userService.register(
            this.userForm.value.firstName,
            this.userForm.value.lastName,
            this.userForm.value.login,
            this.userForm.value.passwordForm.password,
            this.userForm.value.rememberMe
        ).subscribe(
            () => this.router.navigate(['/']),
            () => this.registrationFailed = true,
        );
    }

}
