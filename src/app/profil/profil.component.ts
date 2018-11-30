import { Component, TemplateRef, ViewChild } from '@angular/core';
import { NgbModal } from "@ng-bootstrap/ng-bootstrap";

const colors: any = {
    red: {
        primary: '#ad2121',
        secondary: '#FAE3E3'
    },
    blue: {
        primary: '#1e90ff',
        secondary: '#D1E8FF'
    },
    yellow: {
        primary: '#e3bc08',
        secondary: '#FDF1BA'
    }
};

@Component({
  selector: 'cow-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})

export class ProfilComponent {
@ViewChild('modalContent')
    modalContent: TemplateRef<any>;


    constructor(private modal: NgbModal) {}


}
