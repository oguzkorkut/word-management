import { AddLevelComponent } from './add-level/add-level.component';
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

  filterText: number = 0;

  pager: AppPager = new AppPager();

  levelId: number = 0;

  levels: Level[] = [];

  constructor(private route: ActivatedRoute,private wordService: WordService, 
    private notificationsService: NotificationsService, private activatedRoute: ActivatedRoute,
    public dialog: MatDialog) { }

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
    let dialogRef = this.dialog.open(AddLevelComponent, {
      width: '400px',
      data: null
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result){
       console.log("");
      }
    });
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
    this.wordService.saveLevel(level).subscribe(
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