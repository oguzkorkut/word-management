import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { CookieService } from 'ngx-cookie';
import { routerTransition } from '../../admin/router.animations';
import { UserCredential } from '../../model/userCredential';
import {LoginService} from '../../service/login.service';
import {AuthResponse} from '../../model/authResponse';
import { UserService } from '../../service/user.service';
@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css'],
  animations: [routerTransition()]
}) 
export class UserLoginComponent implements OnInit {

  userCredential: UserCredential = { username: '', password: '' };
  isLoginCredentialCorrect = false;
  errorMessage = '';
  
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private _cookieService: CookieService,
    private userService: UserService,
    private loginService: LoginService
  ) { }

  ngOnInit() {
    if (this._cookieService.get('isLoggedin')) {
      this.userService.checkToken().then((statusResponse => {
        this.router.navigate(['/user/dashboard']);
      }))
        .catch(e => {
          this._cookieService.removeAll();
        });
    }
  }

  onLoggedin() {
     if (this.userCredential.password === '' || this.userCredential.username === '') {
       this.isLoginCredentialCorrect = false;
       this.errorMessage = 'Geçerli kullanıcı adı veya şifre giriniz.';
     } else {
       this.loginService.getAuthenticated(this.userCredential)
         .then(
           (res: AuthResponse) => {

             this.isLoginCredentialCorrect = true;
             this._cookieService.put('Token', res.access_token);
             this._cookieService.put('isLoggedin', 'true');
             this._cookieService.put('username', this.userCredential.username);
            
             //setTimeout(function () {
             this.router.navigate(['/user/dashboard'], { relativeTo: this.route });
             //}, 500);

           }
         )
         .catch(
         (res: Response) => {
           if (res.status === 400) {
             this.isLoginCredentialCorrect = false;
             this.errorMessage = 'Yanlış kullanıcı adı veya şifre girdiniz.';
           } else {
            this.isLoginCredentialCorrect = false;
            this.errorMessage = 'Servis bağlantısında bir hata oluştu.';
           }
         }
         );
     }
  }

  keyDownFunction(event) {
    if (event.keyCode === 13) {
      this.onLoggedin();
    }
  }
}
