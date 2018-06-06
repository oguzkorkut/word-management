import { LogoutService } from './../../service/logout.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserLoginComponent } from './user-login.component';
import { UserLoginRoutingModule } from './user-login-routing.module';
import { FormsModule } from '@angular/forms';
import {LoginService} from '../../service/login.service';
import { UserService } from '../../service/user.service';

@NgModule({
  imports: [CommonModule, UserLoginRoutingModule, FormsModule],
  declarations: [UserLoginComponent],
  providers: [LoginService, UserService],
})
export class UserLoginModule { }
