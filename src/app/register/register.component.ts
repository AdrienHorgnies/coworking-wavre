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

    registrationFailed : boolean;
    loginCtrl: FormControl;
    passwordCtrl: FormControl;
    passwordTestCtrl: FormControl;
    firstNameCtrl: FormControl;
    lastNameCtrl: FormControl;
    userForm: FormGroup;
    passwordForm : FormGroup;

    static passwordMatch(control: FormGroup) {
        const password = control.get('password').value;
        const passwordtest = control.get('passwordtest').value;
        return password !== passwordtest ? { matchingError: true } : null;
    }

    constructor(private fb: FormBuilder, private userService : UserService, private routeur: Router) {
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
                passwordtest: this.passwordTestCtrl
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
                firstname: this.firstNameCtrl,
                lastname: this.lastNameCtrl
        });
    }

    register() {
        this.userService.register(
            this.userForm.value.firstname,
            this.userForm.value.lastname,
            this.userForm.value.login,
            this.userForm.value.passwordForm.password
        ).subscribe(
            () => this.routeur.navigate(['/']),
            () => this.registrationFailed = true,
        );
    }

}
