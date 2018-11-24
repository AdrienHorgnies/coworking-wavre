import {Injectable} from '@angular/core';
import {MeetingModel} from './models/meeting.model';
import {Observable, of} from 'rxjs';
import {delay} from 'rxjs/operators';


// delay retarde le résultat d'une demi-seconde
// of sera remplacé à terme par une requête http qui retournera un tableau de spaces en une seule requête

@Injectable({
    providedIn: 'root'
})
export class MeetingService {

    constructor() {
    }

    // todo you must complete MeetingModel so it has all the attributes you showcase here
    list(): Observable<Array<MeetingModel>> {
        return of([
            {
                id: 1,
                name: 'la travailleuse',
                category: 'Salle de réunion',
                area: 20,
                capacity: 50,
                price: 200,
                resume: 'Cette salle de réunion vous raviera'
            },
            {
                id: 2,
                name: 'la connaisseuse',
                category: 'Salle de formation',
                area: 30,
                capacity: 75,
                price: 300,
                resume: 'Installations complètes pour une bonne formation'
            },
            {
                id: 3,
                name: 'la chançeuse',
                category: 'Salle événementielle',
                area: 300,
                capacity: 150,
                price: 500,
                resume: 'Parfaite pour votre assemblée générale'
            }
        ]).pipe(delay(500));
    }
}
