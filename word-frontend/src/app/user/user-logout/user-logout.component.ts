import { LogoutService } from './../../service/logout.service';
import { Component, OnInit } from '@angular/core';
import { CookieService } from 'ngx-cookie';
import { AuthResponse } from '../../model/authResponse';
import { Router, ActivatedRoute } from '@angular/router';
  
@Component({
  selector: 'app-user-logout',
  templateUrl: './user-logout.component.html',
  styleUrls: ['./user-logout.component.css']
})
export class UserLogoutComponent implements OnInit {

  constructor(private logoutService: LogoutService, private router: Router,
    private route: ActivatedRoute,
    private _cookieService: CookieService) { }

  errorMessage = '';

  ngOnInit() {
    this.logout();
  }

  logout(){
      
    this.logoutService.logout()
    .then(
      (res: AuthResponse) => {
        this._cookieService.removeAll();
        const that = this;
        window.location.href = '/userlogin';
      }
    )
    .catch(
    (res: Response) => {
             
       this.errorMessage = 'Logut sırasında hata oluştu:' + res.statusText;
      
    });

   
  }

}
