import { Injectable, Inject } from '@angular/core';
import {Http, Headers, Response, RequestOptions, RequestOptionsArgs} from '@angular/http';
import {Observable} from 'rxjs/Observable'; // asenkron  servis bağlantıları
import 'rxjs/add/operator/map'; // gelen response data'yi istenilen bir nesneye map etmek icin kullanilir
import 'rxjs/add/operator/do'; // asenkron operasyon bittiginde yapilmasi istenen islemi anlatir.
import 'rxjs/add/operator/catch'; // Bir hata oldugunda yapilmasi istenen sey yazilir
import { ReturnModel } from '../../../../model/ReturnModel';
import { Category } from '../../../../model/Category';
import { Question } from '../../../../model/Question';
import { Answer } from '../../../../model/Answer';
import { CONSTANTS } from '../../../../service/constants';
import { CookieService } from 'ngx-cookie';



@Injectable()
export class CategoryService {

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
/*    
  cerateHeader(){
    let headers = new Headers();

    headers.append('Access-Control-Allow-Origin','*');
    headers.append('Access-Control-Allow-Methods', 'GET, POST, OPTIONS, DELETE, PUT');
    headers.append('Access-Control-Allow-Headers', 'Origin, X-Requested-With, Content-Type, Accept');
   
    headers.append('Accept','application/json');
    headers.append('content-type','application/json');

    return headers;
  }*/

  getCategories(): Observable<ReturnModel> {

    return this.http.get(this.url + '/category/getCategories').map(response => response.json());
  }

  saveCategory(category: Category): Observable<ReturnModel>{

    //let headers = this.cerateHeader();
    this.createHeader();
    
    var requestOptions =new RequestOptions({headers:this.headers});

    return this.http.post(this.url + '/category/save', category, requestOptions).map(response => response.json()).catch(error => this.handleError(error));
  }

  updateCategory(category: Category): Observable<ReturnModel>{
    return this.http.put(this.url + '/category/update', category).map(response => response.json()).catch(error => this.handleError(error));;
  }

  getQuestionsByCategoryId(id: number): Observable<ReturnModel>{
    //let headers = new Headers();
        
    //headers.append('Accept','application/json');
    //headers.append('Content-Type','application/json');

    var requestOptions =new RequestOptions({headers:this.headers});

    return this.http.get(this.url + '/category/getQuestionsByCategoryId/' + id).map(response => response.json()).catch(error => this.handleError(error));;
  }

  saveQuestion(question: Question): Observable<ReturnModel>{

   // let headers = this.cerateHeader();

    var requestOptions =new RequestOptions({headers:this.headers});

    return this.http.post(this.url + '/category/saveQuestion', question, requestOptions).map(response => response.json()).catch(error => this.handleError(error));;
  }

  saveAnswer(answer: Answer): Observable<ReturnModel>{

    //let headers = this.cerateHeader();

    var requestOptions =new RequestOptions({headers:this.headers});

    return this.http.post(this.url + '/category/saveAnswer', answer, requestOptions).map(response => response.json()).catch(error => this.handleError(error));;
  }

  saveAnswers(answers: Answer[]): Observable<ReturnModel>{

    //let headers = this.cerateHeader();

    var requestOptions =new RequestOptions({headers:this.headers});

    return this.http.post(this.url + '/category/saveAnswers', answers, requestOptions).map(response => response.json()).catch(error => this.handleError(error));;
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

