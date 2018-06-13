import { Word } from './../model/Word';
import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'spaceFilter'
})
export class SpaceFilterPipe implements PipeTransform {

  transform(value: string, filterText?: string): string {

    filterText = filterText ? filterText.toLocaleUpperCase() : null;

    return (!filterText) ? '' : filterText.replace(/ /g, '');
  }

}
