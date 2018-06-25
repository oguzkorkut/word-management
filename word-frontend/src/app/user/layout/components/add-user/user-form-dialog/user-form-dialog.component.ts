import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { NotificationsService } from 'angular2-notifications';
import { FormControl, Validators } from '@angular/forms';
import { TextMaskModule } from 'angular2-text-mask';

@Component({
  selector: 'app-user-form-dialog',
  templateUrl: './user-form-dialog.component.html',
  styleUrls: ['./user-form-dialog.component.css']
})
export class UserFormDialogComponent {

  //public mask: Array<string | RegExp>

 
  mask = ['(', /[1-9]/, /\d/, /\d/, ')', ' ', /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/];

  email = new FormControl('', [Validators.required, Validators.email]);

  mobileNumber:string="";
  
  constructor(
    public dialogRef: MatDialogRef<UserFormDialogComponent>,
    private notificationsService: NotificationsService,
    @Inject(MAT_DIALOG_DATA) public data: any) { 

    
    }

    onNoClick(): void {
      this.dialogRef.close();
    }
  
    checkout() {

      if(!this.data.firstname){
        this.notificationsService.warn('Warning','İsim giriniz.');
      }

      if(!this.data.lastname){
        this.notificationsService.warn('Warning','İsim giriniz.');
      }
      
      if(this.email.status === "VALID") {
         this.dialogRef.close(this.data);
      } else {
        this.notificationsService.warn('Warning','Alan boş geçilemez.');
      }
    }

    getErrorMessage() {
      return this.email.hasError('required') ? 'You must enter a value' :
          this.email.hasError('email') ? 'Not a valid email' :
              '';
    }

}
