import { UpdateDialogPanelComponent } from './../update-dialog-panel/update-dialog-panel.component';
import { Word } from './../../../../../../model/Word';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { WordService } from '../../../../../../service/word.service';
import { MatDialog } from '@angular/material';
import { NotificationsService } from 'angular2-notifications';
import { AppPager } from '../../../../../../AppPager';

@Component({
  selector: 'app-words-of-levels',
  templateUrl: './words-of-levels.component.html',
  styleUrls: ['./words-of-levels.component.css']
})
export class WordsOfLevelsComponent implements OnInit {

  public loading = false;

  filterText: string;

  level: number;

  pager: AppPager = new AppPager();

  words:Word[] = [];

  constructor(private route: ActivatedRoute,private wordService: WordService, 
    private notificationsService: NotificationsService, public dialog: MatDialog) { }

  ngOnInit() {

    this.route.params.subscribe(params => {
     
      this.level = params.level;
      console.log(this.level);

      this.getWordsByLevel();
    });
  }

  getWordsByLevel(){
  	this.loading = true;
  	
    this.wordService.getWordsByLevel(this.level).subscribe( response  => {
      console.log(response );
      if(response.result){
        this.words = response.result as Word[];
        this.pager = this.getPager(this.words.length,1,3);
      } 
      
      if(!response.status) {
       this.notificationsService.error('Error',response.message);
      }
         
      this.loading = false;
    },
    error =>{
      this.loading = false;
      console.log(error);
      this.notificationsService.error('Error',error);
    });
  }

  add(inputText: HTMLInputElement){
    if (inputText.value) {

      var control: boolean = true;

      this.words.forEach(word => {
        if(word.name === inputText.value){
            control = false;
            this.notificationsService.warn('Warning', 'Kelime kayıtlı.');
        }
      });

      if(control){
        var word = new Word();
        word.name = inputText.value.split(' ').join('');
        //word.level = this.level;
        word.active = true;
  
  		this.loading = true;
  		
        this.wordService.addWord(this.level, word).subscribe(
          response  => {
            console.log(response )
            if(response.status){
              this.notificationsService.info('Successfull','word is saved!');
              this.getWordsByLevel();
            } else{
              this.notificationsService.error('Error',response.message);
            }
            this.loading = false;
            
        },
        error =>{
          this.loading = false;
          
          console.log(error )
          this.notificationsService.error('Error',error);
        });

        inputText.value = '';
      }
    } else {
      this.notificationsService.warn('Warning','Boş kayıt eklenemez.');
    }
  }

  delete(id:number){
  	this.loading = true;
  	
    this.wordService.deleteWord(id).subscribe(
      response  => {
        console.log(response )
        if(response.status){
          this.notificationsService.info('Successfull','word is deleted!');
          this.getWordsByLevel();
        } else{
          this.notificationsService.error('Error',response.message);
        }
        this.loading = false;
    },
    error =>{
      this.loading = false;
      
      console.log(error )
      this.notificationsService.error('Error',error);
    });
  }

  openUpdateDialogPanel(word:Word){
    var name = word.name;

    let tempWord = new Word;
    
    tempWord.id = word.id;
    tempWord.name = word.name;
    tempWord.active = word.active;

    let dialogRef = this.dialog.open(UpdateDialogPanelComponent, {
      width: '400px',
      data: tempWord
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result){
       console.log(result);
       this.update(result)
      }
    });

  }

  update(word: Word){
    this.wordService.updateWord(word).subscribe(
      response  => {
        console.log(response )
        if(response.status){
          this.notificationsService.info('Successfull','word is updated!');
          this.getWordsByLevel();
        } else{
          this.notificationsService.error('Error',response.message);
        }
        
    },
    error =>{
      console.log(error )
      this.notificationsService.error('Error',error);
    });
  }
  


  
  getPager(totalItems: number, currentPage: number, pageSize: number=10): AppPager{
    let totalPage = Math.ceil(totalItems/pageSize);

    let pages: Array<number>= [];
    for(let i=0 ; i<totalPage; i++){
      pages.push(i+1);
    }

    var pager = new AppPager();

    pager.pageSize= pageSize;
    pager.currentPage = currentPage;
    pager.pageList = pages;

    return pager;
  }

  setPage(page:number){
    this.pager.currentPage= page;
  }

}
