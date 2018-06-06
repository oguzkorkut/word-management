import { User } from './../model/user';
import { StatusResponse } from './../model/assets/statusResponse';
import {CONSTANTS} from './constants';
import {Injectable} from '@angular/core';
import {Headers, Http, Response} from '@angular/http';
import { CookieService } from 'ngx-cookie';
import 'rxjs/add/operator/toPromise';


@Injectable()
export class UserService {
  private serviceUrl = CONSTANTS.server + '/services/rest-api/user';
  private access_token: string;
  private headers: Headers = new Headers();
  constructor(private http: Http, private _cookieService: CookieService) {
    this.createHeader();
  } 
  createHeader() {
    this.access_token = this._cookieService.get('Token');
    this.headers = new Headers();
    this.headers.append('Authorization', 'Bearer ' + this.access_token);
    this.headers.append('Content-Type', 'application/json');
  }
  getLoggedInUser(): Promise<User> {
    this.createHeader();
    return  this.http
        .get(this.serviceUrl, { headers: this.headers })
        .toPromise()
        .then((response) => {
            return response.json() as User;
        })
        .catch(error => this.handleError(error));
  }
  checkToken(): Promise<StatusResponse> {
    const url = `${this.serviceUrl}/${this.access_token}`;

    return this.http
      .get(url, {headers: this.headers})
      .toPromise()
      .then((response) => {
        return response.json() as StatusResponse;
      })
      .catch(error => this.handleError(error));
  }
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    if (error.status === 401) {
      this._cookieService.removeAll();
      window.location.href = '/userlogin';
    }
    return Promise.reject(error.message || error);
  }

}
