import { Component, TemplateRef, ViewChild } from '@angular/core';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

@Component({
    selector: 'cow-profile',
    templateUrl: './profile.component.html',
    styleUrls: ['./profile.component.css']
})

export class ProfileComponent {
@ViewChild('modalContent')
    modalContent: TemplateRef<any>;

    constructor(private modal: NgbModal) {}

}
