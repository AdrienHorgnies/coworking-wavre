import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { UserModel } from "./models/user.model";

@Injectable({
    providedIn: 'root'
})

export class UserService {

    constructor(private http: HttpClient) {
    }

    register(firstName: string, lastName: string, email: string, password: string, rememberMe: boolean): Observable<any> {
        const body = {firstName, lastName, email, password, rememberMe};
        return this.http.post<UserModel>('http://localhost:8080/api/register', body);
    }

}
