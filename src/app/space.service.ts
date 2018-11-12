import {Injectable} from '@angular/core';
import {SpaceModel} from './models/space.model';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';


// delay retarde le résultat d'une demi-seconde
// of sera remplacé à terme par une requête http qui retournera un tableau de spaces en une seule requête

@Injectable({
    providedIn: 'root'
})
export class SpaceService {

    constructor() {
    }

    // todo you must complete SpaceModel so it has all the attributes you showcase here
    list(): Observable<Array<SpaceModel>> {
        return of([
            {
                id: 1,
                name: 'le merveilleux',
                category: 'bureau privatif',
                area: 6,
                capacity: 3,
                price: 2000,
                resume: 'espace de travail privatif qui vous séduira par son côté intime'
            },
            {
                id: 2,
                name: 'la belle',
                category: 'bubble',
                area: 2,
                capacity: 1,
                price: 1000,
                resume: 'Vous serez dans votre bulle pour travailler'
            },
            {
                id: 3,
                name: 'la splendide',
                category: 'bureau partagé',
                area: 20,
                capacity: 9,
                price: 200,
                resume: 'Espace de coworking parfait pour élargir votre réseau'
            }
        ]).pipe(delay(500));
    }
}
