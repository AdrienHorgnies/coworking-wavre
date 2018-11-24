import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'cow-bookingspace',
  templateUrl: './bookingspace.component.html',
  styleUrls: ['./bookingspace.component.css']
})
export class BookingspaceComponent implements OnInit {

    content = "";

    // todo: initialiser le nombre de personnes >1 et <capacity
    numberpers: string = "number";


    constructor() { }

  ngOnInit() {
  }

}
