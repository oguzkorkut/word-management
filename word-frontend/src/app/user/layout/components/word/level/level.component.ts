import { AddLevelDialogPanelComponent } from './add-level-dialog-panel/add-level-dialog-panel.component';
import { Level } from './../../../../../model/Level';
import { WordService } from './../../../../../service/word.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AppPager } from '../../../../../AppPager';
import { NotificationsService } from 'angular2-notifications';
import { MatDialog } from '@angular/material';

@Component({
  selector: 'app-level',
  templateUrl: './level.component.html',
  styleUrls: ['./level.component.css']
})
export class LevelComponent implements OnInit {

  public loading = false;

  filterText: number;

  pager: AppPager = new AppPager();

  levelId: number = 0;

  levels: Level[] = [];

  constructor(private route: ActivatedRoute,private wordService: WordService, 
    private notificationsService: NotificationsService, public dialog: MatDialog) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.loading = true;
     

      if(params['id']){
        this.levelId = params['id'];
        this.getLevelssByLevelId(params['id']);
      } else{
        this.getAllLevels();
      }

      this.loading = false;
      
    });
  }

  openAddLevel(){
    var lastLevel = this.getLastLevel();

    let tempLevel = new Level;
    tempLevel.active = true;
    tempLevel.level = lastLevel + 1;
    tempLevel.name = "";
    tempLevel.words = [];

    let dialogRef = this.dialog.open(AddLevelDialogPanelComponent, {
      width: '400px',
      data: tempLevel
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result){
       console.log(result);
       this.addLevel(result);
      }
    });
  }

  getLastLevel():number{
    var lastLevel = 0;

    if(this.levels){
      this.levels.forEach(l => {
        if(l.level > lastLevel){
          lastLevel = l.level;
        }
      });
    }
    return lastLevel;
  }


  getLevelssByLevelId(id:number){
    this.wordService.getLevelById(id).subscribe( response  => {
      console.log(response );
      this.levels = response.result as Level[];

      this.pager = this.getPager(this.levels.length,1,3);
    },
    error =>{
      console.log(error);
      this.notificationsService.error('Error',error);
    });
  }

  getAllLevels(){
    this.wordService.getLevels().subscribe( response  => {
      console.log(response );
      if(response.result){
        this.levels = response.result as Level[];
        this.pager = this.getPager(this.levels.length,1,3);
      }      
    },
    error =>{
      console.log(error);
      this.notificationsService.error('Error',error);
    });
  }

  addLevel(level: Level){
    this.wordService.addLevel(level).subscribe(
      response  => {
        console.log(response )
        this.notificationsService.info('Successfull','Level is saved!');
        this.getAllLevels();
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