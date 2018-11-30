import { Injectable } from '@angular/core';
import { MakeReservationModel } from "./models/makeReservationModel";
import { environment } from "../environments/environment";
import { HttpClient } from '@angular/common/http';
import { ReservationModel } from "./models/reservation.model";

@Injectable({
    providedIn: 'root'
})
export class ReservationService {

    constructor(private http: HttpClient) {
    }

    make(reservation: MakeReservationModel) {
        return this.http.post(`${environment.apiUrl}/reservations`, reservation);
    }

    listOwn() {
        return this.http.get<Array<ReservationModel>>(`${environment.apiUrl}/reservations/own`);
    }

    get(id: number) {
        return this.http.get<ReservationModel>(`${environment.apiUrl}/reservations/${id}`);
    }
}
