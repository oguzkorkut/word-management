import { Word } from './../model/Word';
import { Level } from './../model/Level';
import { Injectable } from '@angular/core';
import { CONSTANTS } from './constants';
import {Http, Headers, Response, RequestOptions, RequestOptionsArgs} from '@angular/http';
import { CookieService } from 'ngx-cookie';
import { ReturnModel } from '../model/ReturnModel';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class WordService {

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
  
    url: string = CONSTANTS.server + '/services/api';    
  
    addLevel(level: Level): Observable<ReturnModel>{
        
      var requestOptions =new RequestOptions({headers:this.headers});
  
      return this.http.post(this.url + '/word/level/save', level, requestOptions).map(response => response.json()).catch(error => this.handleError(error));
    }

    addWord(level: number, word: Word): Observable<ReturnModel>{
      
      var requestOptions =new RequestOptions({headers:this.headers});
  
      var u = this.url + '/word/save?level=' + level;
      return this.http.post(u, word, requestOptions).map(response => response.json()).catch(error => this.handleError(error));
    }
  
    getLevels(): Observable<ReturnModel>{
        
      var requestOptions =new RequestOptions({headers:this.headers});
  
      return this.http.get(this.url + '/word/getLevels', requestOptions).map(response => response.json()).catch(error => this.handleError(error));
    }

    getWordsByLevel(level: number): Observable<ReturnModel>{
        
      var localHeaders = new Headers();
      localHeaders.append('Authorization', 'Bearer ' + this.access_token);
      localHeaders.append('Content-Type', 'application/json');
      localHeaders.append('Accept','application/json');
      
      var requestOptions =new RequestOptions({headers:localHeaders});
  
      return this.http.get(this.url + '/word/getWordsByLevel?level=' + level, requestOptions).map(response => response.json()).catch(error => this.handleError(error));
    }

    getLevelById(id:number): Observable<ReturnModel>{
        
      var requestOptions = new RequestOptions({headers:this.headers});
  
      return this.http.get(this.url + '/word/getLevelById/' + id).map(response => response.json()).catch(error => this.handleError(error));;
    }

    deleteWord(id: number): Observable<ReturnModel>{
      
      var requestOptions =new RequestOptions({headers:this.headers});
  
      var u = this.url + '/word/delete/' + id ;
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
