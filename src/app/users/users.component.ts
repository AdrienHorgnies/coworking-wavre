import { Component, OnInit } from '@angular/core';
import {UserModel} from "../Models/user.model";

@Component({
  selector: 'cow-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

    users : Array<UserModel> = [];


  constructor() { }

  ngOnInit()
  {
      this.users =
          [
            {   id : 1,
                firstname : 'Phirum',
                lastname : 'chan',
                email : 'chan.1804.student@ifosupwavre.be',
                Password : 'test123',
            },
              {   id : 2,
                  firstname : 'Katia',
                  lastname : 'Baran',
                  email : 'Baran.1804.student@ifosupwavre.be',
                  Password : 'test123',
              },
          ];
  }

}
