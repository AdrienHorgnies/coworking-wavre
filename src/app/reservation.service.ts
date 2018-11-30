import { Injectable } from '@angular/core';
import { MakeReservationModel } from "./models/makeReservationModel";
import { environment } from "../environments/environment";
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class ReservationService {

    constructor(private http: HttpClient) {
    }

    make(reservation: MakeReservationModel) {
        return this.http.post(`${environment.apiUrl}/reservations`, reservation);
    }
}
