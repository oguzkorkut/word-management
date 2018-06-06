import { Word } from './Word';
export class Level {

    id: number;
    name: string;
    level: number;
    words: Word[] = [];
    active: boolean;   
  
  }