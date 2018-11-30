import { Component, OnDestroy, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";
import { UserService } from "../user.service";
import { UserModel } from "../models/user.model";
import { Subscription } from 'rxjs';

@Component({
    selector: 'cow-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})

export class ProfileComponent implements OnInit, OnDestroy {
    @ViewChild('modalContent')
    modalContent: TemplateRef<any>;

    user: UserModel;
    userSubscription: Subscription;

    constructor(private userService: UserService, private modal: NgbModal) {
    }

    ngOnInit() {
        this.userSubscription = this.userService.getCurrentlyLoggedInUser().subscribe(
            user => this.user = user
        );
    }

    ngOnDestroy() {
        if (this.userSubscription) {
            this.userSubscription.unsubscribe();
        }
    }
}
