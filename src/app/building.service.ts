import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from "../environments/environment";
import { BuildingModel } from "./models/building.model";

@Injectable({
    providedIn: 'root'
})
export class BuildingService {

    constructor(private http: HttpClient) {
    }

    buildingsWithSpaces() {
        return this.http.get<Array<BuildingModel>>(`${environment.apiUrl}/buildings/with-spaces`);
    }
}
