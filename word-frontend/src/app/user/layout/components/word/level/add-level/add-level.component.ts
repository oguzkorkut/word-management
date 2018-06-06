import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'app-add-level',
  templateUrl: './add-level.component.html',
  styleUrls: ['./add-level.component.css']
})
export class AddLevelComponent {
 
 
  constructor(
    public dialogRef: MatDialogRef<AddLevelComponent>,
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