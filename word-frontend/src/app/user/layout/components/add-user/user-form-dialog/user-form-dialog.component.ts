import { Role } from './../../../../../model/role';
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
  
    checkout(phoneInput: HTMLInputElement) {

      var control = true;
      if(typeof this.data.active === "undefined"){ 
        this.data.active = true;
      }
      if(!this.data.firstname){
        control = false;
        this.notificationsService.warn('Warning','İsim giriniz.');
      } 
      if(!this.data.surname){
        control = false;
        this.notificationsService.warn('Warning','Soyad giriniz.');
      } 
      if(!this.data.surname){
        control = false;
        this.notificationsService.warn('Warning','İsim giriniz.');
      } 
      if(this.email.status != "VALID") {
        control = false;
        this.notificationsService.warn('Warning','Geçerli bir e-posta adresi giriniz.');
      } 
      if(!this.data.phone){
        control = false;
        this.notificationsService.warn('Warning','Telefon numarası giriniz.');
      } else {
        if(this.data.phone.split("-")[1].split("_").length > 1){
          control = false;
          this.notificationsService.warn('Warning','Telefon numarası giriniz.');
        }
      }
      if(!this.data.role){
        
        control = false;
        this.notificationsService.warn('Warning','Rol seçiniz..');
        
      } else if(this.data.role){
        var roles: Role[] = [], role= new Role;
        role.name = this.data.role;
        roles.push(role);
        
        this.data.roles = roles;
      }
      if(control) {
        this.dialogRef.close(this.data);
      }
      
    }

    getErrorMessage() {
      return this.email.hasError('required') ? 'You must enter a value' :
          this.email.hasError('email') ? 'Not a valid email' :
              '';
    }

}
