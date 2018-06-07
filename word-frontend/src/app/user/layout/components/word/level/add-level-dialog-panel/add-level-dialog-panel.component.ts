import { Level } from './../../../../../../model/Level';
import { Component, OnInit, Inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotificationsService } from 'angular2-notifications';

@Component({ 
  selector: 'app-add-level-dialog-panel',
  templateUrl: './add-level-dialog-panel.component.html',
  styleUrls: ['./add-level-dialog-panel.component.css']
})
export class AddLevelDialogPanelComponent {
 
 
  constructor(
    public dialogRef: MatDialogRef<AddLevelDialogPanelComponent>,
    private notificationsService: NotificationsService,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

    onNoClick(): void {
      this.dialogRef.close();
    }
  
    checkout() {
      if(this.data.name) {
         this.dialogRef.close(this.data);
      } else {
        this.notificationsService.warn('Warning','Level tanımı giriniz.');
      }
    }
}