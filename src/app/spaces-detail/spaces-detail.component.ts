import { Component, OnInit } from '@angular/core';
import { SpaceService} from "../space.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'cow-spaces-detail',
  templateUrl: './spaces-detail.component.html',
  styleUrls: ['./spaces-detail.component.css']
})
export class SpacesDetailComponent implements OnInit {
    name : string;
    area: number;
    price: number;
    resume: string


  constructor(private spaceService: SpaceService, private  route: ActivatedRoute) { }

  ngOnInit() {
      
      const id = +this.route.snapshot.paramMap.get('id');
      this.name = this.spaceService.getSpaceById(id).name;
      this.area = this.spaceService.getSpaceById(id).area;
      this.price = this.spaceService.getSpaceById(id).price;
      this.resume = this.spaceService.getSpaceById(id).resume;
  }




}
