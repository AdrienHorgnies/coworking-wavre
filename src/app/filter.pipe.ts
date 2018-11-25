import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'filter'
})
export class FilterPipe implements PipeTransform {

    transform(spaces: any, term: any): any {
        if (term === undefined) return spaces;
        return spaces.filter(function(space){
            return space.name.toLowerCase().includes(term.toLowerCase());
        })
    }

}
