import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { map, mapTo, tap } from "rxjs/operators";
import { JwtInterceptorService } from "./jwt-interceptor.service";
import { UserModel } from "./models/user.model";

@Injectable({
    providedIn: 'root'
})

export class UserService {

    public $userEvents = new BehaviorSubject<UserModel>(undefined);

    constructor(private http: HttpClient,
                private jwtInterceptorService: JwtInterceptorService) {
    }

    register(firstName: string, lastName: string, email: string, password: string, rememberMe: boolean): Observable<any> {
        const body = {firstName, lastName, email, password, rememberMe};
        return this.http.post<HttpResponse<Object>>('http://localhost:8080/api/register', body, {observe: 'response'}).pipe(
            tap(resp => this.storeAuthenticationToken(resp, rememberMe)),
            mapTo(() => this.http.get<UserModel>('http://localhost:8080/api/users/self').subscribe(user => this.$userEvents.next(user)))
        );
    }

    authenticate(email: string, password: string, rememberMe: boolean) {
        const body = {email, password, rememberMe};
        return this.http.post<HttpResponse<Object>>('http://localhost:8080/api/authenticate', body, {observe: 'response'}).pipe(
            tap(resp => {
                this.storeAuthenticationToken(resp, rememberMe);
                console.log("stored jwt")
            }),
            map(() => this.http.get<UserModel>('http://localhost:8080/api/users/self').subscribe(user => {
                console.log("emitting user " + user);
                this.$userEvents.next(user)
            }))
        );
    }

    storeAuthenticationToken(resp: HttpResponse<Object>, rememberMe) {
        const jwt = resp.headers.get('Authorization');
        if (jwt) {
            this.jwtInterceptorService.setJwtToken(jwt);
            if (rememberMe) {
                window.localStorage.setItem('jwt', jwt);
            } else {
                window.sessionStorage.setItem('jwt', jwt);
            }
        }
    }
}
