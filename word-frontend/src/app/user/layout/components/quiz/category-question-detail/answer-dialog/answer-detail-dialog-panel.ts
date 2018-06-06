import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { NotificationsService } from "angular2-notifications";
import { Component, Inject } from "@angular/core";
import { Answer } from "../../../../../../model/Answer";

@Component({
    selector: 'answer-detail-dialog-panel',
    templateUrl: 'answer-dialog-panel.html',
    styleUrls: ['answer-dialog-panel.css']
  })
  export class AnswerDetailDialogPanel {
  
    constructor(
      public dialogRef: MatDialogRef<AnswerDetailDialogPanel>,
      private notificationsService: NotificationsService,
      @Inject(MAT_DIALOG_DATA) public data: any) { }
  
      onNoClick(): void {
        this.dialogRef.close();
      }
    
      checkout() {
        console.log("checkout answers");
        if(this.data.answerDtos.length > 0) {
           
          let control = false;
  
          let count: number = 0;
  
          for (let i = 0; i < this.data.answerDtos.length; i++) {
            const element = this.data.answerDtos[i];
            if (element.result && !element.deleted) {
              control = true;
              count++;
            }
            
          }
  
          if (control) {
            if (count > 1) {
              this.notificationsService.warn('Warning','Birden fazla doğru cevap seçilemez.');
            }else{
              this.dialogRef.close(this.data);
            }
            
          }else{
            this.notificationsService.warn('Warning','Sorunun doğru cevabını giriniz.');
          }
          
        } else {
          this.notificationsService.warn('Warning','Cevap girişi zorunludur.');
        }
      }
  
      changeResult(answer: Answer) {
        answer.result = !answer.result;
      }
  
      delete(answer: Answer, index: number) {
        answer.deleted = !answer.deleted;
        if(!answer.id){
          this.data.answerDtos.splice(index, 1);
        }
        
      }
      
  
      addAnswer(questionId:number ,inputText: HTMLInputElement){
  
        if (inputText.value) {
          let tempAnswer: Answer = new Answer();
    
          tempAnswer.answer = inputText.value;
          tempAnswer.result = false;
          tempAnswer.active = true;
          tempAnswer.questionId = questionId;
       
          this.data.answerDtos.push(tempAnswer);
  
          this.notificationsService.success('Bilgi Mesajı','Cevap eklendi.');
  
          inputText.value = '';
          /* 
          this.categoryService.saveAnswer(tempAnswer).subscribe(
            response  => {
              console.log(response )
              this.notificationsService.info('Successfull','Answer is saved!');
              this.getQuestionsByCategoryId(this.categoryId);
          },
          error =>{
            console.log(error )
          });*/
        } else {
          this.notificationsService.warn('Hata','Boş kayıt eklenemez.')
        }    
      }
  
  }