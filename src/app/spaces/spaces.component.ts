import { Component, OnInit } from '@angular/core';
import { SpaceModel } from '../models/space.model';

@Component({
  selector: 'cow-spaces',
  templateUrl: './spaces.component.html',
  styleUrls: ['./spaces.component.css']
})


export class SpacesComponent implements OnInit {

  spaces: Array<SpaceModel> = [];
  constructor() { }

  ngOnInit() {
      this.spaces = [{ name: 'Bureau privatif' }, { name: 'Bureau partagé' }, { name: 'Bubble' }];
  }

}
