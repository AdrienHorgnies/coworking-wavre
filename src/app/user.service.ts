import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable} from "rxjs/index";
import { UserModel} from "./Models/user.model";

@Injectable ({
    providedIn: 'root'
})

export class UserService {

constructor (private http: HttpClient  ){}

register(firstname: string, lastname: string, email: string, password: string): Observable<any> {
        const body = { firstname, lastname, email, password };
        return this.http.post<UserModel>('http://localhost:8080/api/users', body);
    }


}

