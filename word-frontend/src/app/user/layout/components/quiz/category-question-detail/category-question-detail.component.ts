import { Answer } from './../../../../../model/Answer';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { CategoryService } from './../category.service';
import { Component, OnInit, Inject } from '@angular/core';
import { NotificationsService } from 'angular2-notifications';
import { ActivatedRoute } from '@angular/router';

import { NgForm } from '@angular/forms';
import { Question } from '../../../../../model/Question';
import { AppPager } from '../../../../../AppPager';
import { QuestionDetailDialogPanel } from './question-dialog/question-detail-dialog-panel';
import { AnswerDetailDialogPanel } from './answer-dialog/answer-detail-dialog-panel';


@Component({
  selector: 'app-category-question-detail',
  templateUrl: './category-question-detail.component.html',
  styleUrls: ['./category-question-detail.component.css'],
  providers: [CategoryService]
})
export class CategoryQuestionDetailComponent implements OnInit {
  
  operationName: string = "";
  public loading = false;

  constructor(private route: ActivatedRoute,private categoryService: CategoryService, 
    private notificationsService: NotificationsService, private activatedRoute: ActivatedRoute,
    public dialog: MatDialog) { }

  questions: Question[] = [];

  filterText: string = '';

  pager: AppPager = new AppPager();

  categoryId: number = 0;

  ngOnInit() {
    this.route.params.subscribe(params => {
     
      this.operationName = params.operationName;
      console.log(this.operationName);

      if(params['id']){
        this.categoryId = params['id'];
        this.getQuestionsByCategoryId(params['id']);
      }
      
    });
    
  }
  getQuestionsByCategoryId(id: number){
    this.loading = true;
   
    this.categoryService.getQuestionsByCategoryId(id).subscribe( response  => {
      console.log(response );
      this.questions = response.result as Question[];

      this.pager = this.getPager(this.questions.length,1,3);
      
      this.loading = false;
    },
    error =>{
      this.loading = false;
      console.log(error )
    });
  }

  addQuestion(question: Question){
  	this.loading = true;
  
    this.categoryService.saveQuestion(question).subscribe(
      response  => {
        console.log(response )
        this.notificationsService.info('Successfull','Question is saved!');
        this.getQuestionsByCategoryId(this.categoryId);
        this.loading = false;
    },
    error =>{
      this.loading = false;
      console.log(error )
    });
  }

  openQuestionDialog(id: number): void {
    let dialogRef = this.dialog.open(QuestionDetailDialogPanel, {
      width: '400px',
      data: { categoryId: id, questionType: 'SINGLE_CHOICE', question: ''}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      if(result){
        this.addQuestion(result);
      }
    });
  }

  openAnswerDialog(questionId: number,question: string, answerDtos:Answer[]): void {
   
   // let newAnswerDtos= Object.assign({}, answerDtos);

    let newAnswerDtos = answerDtos.map(x => Object.assign({}, x));

    let dialogRef = this.dialog.open(AnswerDetailDialogPanel, {
      width: '500px',
      data: { questionId: questionId,question: question, answerDtos: newAnswerDtos}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.questions = result;
      //this.addQuestion(result);
      if(result && result.answerDtos){
        this.addAnswers(result.answerDtos)
      }
    });
  }

  addAnswers(answerDtos: Answer[]){
    this.categoryService.saveAnswers(answerDtos).subscribe(
      response  => {
        console.log(response )
        this.notificationsService.info('Successfull','İşlem başarılı.');
        this.getQuestionsByCategoryId(this.categoryId);
    },
    error =>{
      console.log(error )
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