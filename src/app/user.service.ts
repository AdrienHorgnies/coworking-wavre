import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from "@angular/common/http";
import { Observable } from "rxjs";
import { tap } from "rxjs/operators";
import { JwtInterceptorService } from "./jwt-interceptor.service";

@Injectable({
    providedIn: 'root'
})

export class UserService {

    constructor(private http: HttpClient,
                private jwtInterceptorService: JwtInterceptorService) {
    }

    register(firstName: string, lastName: string, email: string, password: string, rememberMe: boolean): Observable<any> {
        const body = {firstName, lastName, email, password, rememberMe};
        return this.http.post<HttpResponse<Object>>('http://localhost:8080/api/register', body, {observe: 'response'}).pipe(
            tap(resp => {
                const jwt = resp.headers.get('Authorization');
                if (jwt) {
                    this.storeAuthenticationToken(jwt, rememberMe);
                }
            })
        );
    }

    storeAuthenticationToken(jwt, rememberMe) {
        this.jwtInterceptorService.setJwtToken(jwt);
        if (rememberMe) {
            window.localStorage.setItem('jwt', jwt);
        } else {
            window.sessionStorage.setItem('jwt', jwt);
        }
    }
}
