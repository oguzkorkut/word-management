import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotificationsService } from 'angular2-notifications';

@Component({
  selector: 'app-update-dialog-panel',
  templateUrl: './update-dialog-panel.component.html',
  styleUrls: ['./update-dialog-panel.component.css']
})
export class UpdateDialogPanelComponent{

 
  constructor(
    public dialogRef: MatDialogRef<UpdateDialogPanelComponent>,
    private notificationsService: NotificationsService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

    onNoClick(): void {
      this.dialogRef.close();
    }
  
    checkout() {
      if(this.data.name) {
         this.dialogRef.close(this.data);
      } else {
        this.notificationsService.warn('Warning','Kelime giriniz!');
      }
    }
}
