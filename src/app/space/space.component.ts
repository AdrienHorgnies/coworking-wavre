import { Component, Input, OnInit } from '@angular/core';
import { SpaceModel } from '../models/space.model';

@Component({
  selector: 'cow-space',
  templateUrl: './space.component.html',
  styleUrls: ['./space.component.css']
})
export class SpaceComponent implements OnInit {

    @Input() spaceModel: SpaceModel;

  constructor() { }

  ngOnInit() {
  }

}
