import { Role } from './../model/role';
import { ReturnModel } from './../model/ReturnModel';
import { Observable } from 'rxjs/Observable';
import { User } from './../model/user';
import { StatusResponse } from './../model/assets/statusResponse';
import {CONSTANTS} from './constants';
import {Injectable} from '@angular/core';
import { Headers, Http, Response, RequestOptions } from '@angular/http';
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

  getRoles(): Observable<ReturnModel>{
         
    var localHeaders = new Headers();
    localHeaders.append('Authorization', 'Bearer ' + this.access_token);
    localHeaders.append('Content-Type', 'application/json');
    localHeaders.append('Accept','application/json');
    
    var requestOptions =new RequestOptions({headers:localHeaders});

    return this.http.get(this.serviceUrl + '/role/getRoles', requestOptions).map(response => response.json()).catch(error => this.handleError(error));
  }

  addRole(role: Role): Observable<ReturnModel>{
        
    var requestOptions =new RequestOptions({headers:this.headers});

    return this.http.post(this.serviceUrl + '/role/save', role, requestOptions).map(response => response.json()).catch(error => this.handleError(error));
  }

  deleteRole(id: number): Observable<ReturnModel>{
      
    var requestOptions =new RequestOptions({headers:this.headers});

    var u = this.serviceUrl + '/role/delete/' + id ;
    return this.http.delete(u, requestOptions).map(response => response.json()).catch(error => this.handleError(error));
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
