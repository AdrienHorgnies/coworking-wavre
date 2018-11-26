import { Injectable } from '@angular/core';
import { environment } from "../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class ImageService {

    constructor() {
    }

    get(imageRelativeUrl: String) {
        return environment.apiUrl + "/images" + imageRelativeUrl
    }
}
