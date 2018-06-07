import { Pipe, PipeTransform } from '@angular/core';
import { Level } from '../model/Level';




@Pipe({
  name: 'levelFilter'
})
export class LevelFilterPipe implements PipeTransform {

  transform(value: Level[], levelNo?: number): Level[] {

    levelNo = levelNo ? levelNo : 0;

    levelNo = Number(levelNo)

    return levelNo != 0 ? value.filter((p: Level) => p.level == levelNo  ) : value;
  }

}
