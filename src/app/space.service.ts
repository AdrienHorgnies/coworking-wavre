import { Injectable } from '@angular/core';
import { SpaceModel } from "./models/space.model";
import { HttpClient } from '@angular/common/http';
import { environment } from "../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class SpaceService {

    constructor(private http: HttpClient) {

    }

    list() {
        return this.http.get<Array<SpaceModel>>(`${environment.apiUrl}/spaces`);
    }

    get(id: number) {
        return this.http.get<SpaceModel>(`${environment.apiUrl}/spaces/${id}`);
    }

    search(query: string) {
        if (query) {
            return this.http.get<Array<SpaceModel>>(`${environment.apiUrl}/spaces/_search?query=${query}`);
        }
        return this.list();
    }

    minPrice() {
        return this.http.get<number>(`${environment.apiUrl}/spaces/price/min`);
    }

    maxPrice() {
        return this.http.get<number>(`${environment.apiUrl}/spaces/price/max`);
    }
}
