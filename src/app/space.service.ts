import { Injectable } from '@angular/core';
import { SpaceModel } from "./models/space.model";
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class SpaceService {

    constructor(private http: HttpClient) {

    }

    list() {
        return this.http.get<Array<SpaceModel>>('http://localhost:8080/api/spaces');
    }

    get(id: number) {
        return this.http.get<SpaceModel>(`http://localhost:8080/api/spaces/${id}`);
    }
}
