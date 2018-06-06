import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { NotificationsService } from "angular2-notifications";
import { Inject, Component } from "@angular/core";

@Component({ 
    selector: 'question-detail-dialog-panel',
    templateUrl: 'question-dialog-panel.html',
    styleUrls: ['question-dialog-panel.css']
  })
  export class QuestionDetailDialogPanel {
  
    constructor(
      public dialogRef: MatDialogRef<QuestionDetailDialogPanel>,
      private notificationsService: NotificationsService,
      @Inject(MAT_DIALOG_DATA) public data: any) { }
  
      onNoClick(): void {
        this.dialogRef.close();
      }
    
      checkout() {
        
        if(this.data.question) {
           this.dialogRef.close(this.data);
        } else {
          this.notificationsService.warn('Warning','Soru alanı boş geçilemez.');
        }
      }
  
  }