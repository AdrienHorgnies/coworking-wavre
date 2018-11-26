import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { BehaviorSubject, Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { environment } from "../environments/environment";
import { JwtInterceptorService } from "./jwt-interceptor.service";

@Injectable({
    providedIn: 'root'
})

export class UserService {

    public $isUserLoggedIn = new BehaviorSubject<boolean>(false);

    constructor(private http: HttpClient,
                private jwtInterceptorService: JwtInterceptorService) {
        this.retrieveJwt();
    }

    register(firstName: string, lastName: string, email: string, password: string, rememberMe: boolean): Observable<any> {
        const body = {firstName, lastName, email, password, rememberMe};
        return this.http.post<HttpResponse<Object>>(`${environment.apiUrl}/register`, body, {observe: 'response'}).pipe(
            tap(resp => this.storeAuthenticationToken(resp, rememberMe))
        );
    }

    authenticate(email: string, password: string, rememberMe: boolean) {
        const body = {email, password, rememberMe};
        return this.http.post<HttpResponse<Object>>(`${environment.apiUrl}/authenticate`, body, {observe: 'response'}).pipe(
            tap(resp => this.storeAuthenticationToken(resp, rememberMe))
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
        this.$isUserLoggedIn.next(true);
    }

    logout() {
        this.$isUserLoggedIn.next(false);
        window.sessionStorage.removeItem('jwt');
        window.localStorage.removeItem('jwt');
    }

    retrieveJwt() {
        const jwt = window.sessionStorage.getItem('jwt') || window.localStorage.getItem('jwt');
        // todo should verify jwt validity
        if (jwt) {
            this.jwtInterceptorService.setJwtToken(jwt);
            this.$isUserLoggedIn.next(true);
        }
    }
}
