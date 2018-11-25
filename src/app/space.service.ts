import {Injectable} from '@angular/core';
import {SpaceModel} from "./models/space.model";
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
    
    private listSpaces : SpaceModel[] =
        [
            {
                "id": 3,
                "name": "War Room",
                "type": "PRIVATE",
                "peopleCapacity": 10,
                "area": 20,
                "building": {
                    "id": 2,
                    "name": "Le bureau des anges",
                    "address": "Rue du Paradis 3",
                    "city": {
                        "id": 120,
                        "name": "Wavre",
                        "zipCode": 1300
                    },
                    "serviceTypes": [
                        {
                            "id": 1,
                            "name": "Accueil"
                        },
                        {
                            "id": 2,
                            "name": "Courrier"
                        },
                        {
                            "id": 3,
                            "name": "Secrétariat"
                        },
                        {
                            "id": 6,
                            "name": "Salle de réunion"
                        }
                    ]
                },
                "equipmentTypes": [
                    {
                        "id": 1,
                        "name": "Ordinateur"
                    },
                    {
                        "id": 2,
                        "name": "Imprimante"
                    },
                    {
                        "id": 4,
                        "name": "Scanner"
                    },
                    {
                        "id": 5,
                        "name": "Armoire"
                    }
                ]
            }
        ];

    list(): Observable<Array<SpaceModel>> {
        return of([
            {
                "id": 3,
                "name": "War Room",
                "type": "PRIVATE",
                "peopleCapacity": 10,
                "area": 20,
                "building": {
                    "id": 2,
                    "name": "Le bureau des anges",
                    "address": "Rue du Paradis 3",
                    "city": {
                        "id": 120,
                        "name": "Wavre",
                        "zipCode": 1300
                    },
                    "serviceTypes": [
                        {
                            "id": 1,
                            "name": "Accueil"
                        },
                        {
                            "id": 2,
                            "name": "Courrier"
                        },
                        {
                            "id": 3,
                            "name": "Secrétariat"
                        },
                        {
                            "id": 6,
                            "name": "Salle de réunion"
                        }
                    ]
                },
                "equipmentTypes": [
                    {
                        "id": 1,
                        "name": "Ordinateur"
                    },
                    {
                        "id": 2,
                        "name": "Imprimante"
                    },
                    {
                        "id": 4,
                        "name": "Scanner"
                    },
                    {
                        "id": 5,
                        "name": "Armoire"
                    }
                ]
            }
        ]).pipe(delay(500));
    }

    getSpaceById(id: number): SpaceModel{
        return this.listSpaces.find(e => e.id === id);
    }

}
