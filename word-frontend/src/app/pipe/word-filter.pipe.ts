import { Word } from './../model/Word';
import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'wordFilter'
})
export class WordFilterPipe implements PipeTransform {

  transform(value: Word[], filterText?: string): Word[] {

    filterText = filterText ? filterText.toLocaleLowerCase() : null;

    return filterText ? value.filter((p: Word) => p.name.toLocaleLowerCase().indexOf(filterText) !== -1 ) : value;
  }

}
