import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class JwtInterceptorService implements HttpInterceptor {

    token: string | null;

    constructor() {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (this.token) {
            const newReq = req.clone({setHeaders: {'Authorization': `${this.token}`}});
            return next.handle(newReq);
        }
        return next.handle(req);
    }

    setJwtToken(token: string) {
        this.token = token;
    }

    removeJwtToken() {
        this.token = null;
    }
}
