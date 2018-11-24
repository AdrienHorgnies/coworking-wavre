import { Component, OnInit } from '@angular/core';
import {toDate} from "@angular/common/src/i18n/format_date";
import {formatDate} from "@angular/common";

@Component({
  selector: 'cow-bookingspace',
  templateUrl: './bookingspace.component.html',
  styleUrls: ['./bookingspace.component.css']
})
export class BookingspaceComponent implements OnInit {

    content = "";
    date1: string = "25/02/2019";

    date2 = "";
    // totaldate = {{date1}};

    // todo: initialiser le nombre de personnes >1 et <capacity
    numberpers: string = "number";

    total(montant: string): string {
        return montant;

}
    constructor() { }

  ngOnInit() {
  }

}
