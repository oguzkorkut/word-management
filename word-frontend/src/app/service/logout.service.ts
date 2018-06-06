import { CONSTANTS } from './constants';
import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers, URLSearchParams } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import { Md5 } from 'ts-md5/dist/md5';

@Injectable()
export class LogoutService {

    public results: string;

    constructor(private http: Http) {
    }


    logout() {

        const url = CONSTANTS.server + '/oauth/logout';

        const encoded = btoa(CONSTANTS.clientId + ':' + CONSTANTS.secret);
        const basicHeader = 'Basic ' + encoded;
        const headers = new Headers({
            'Content-type': 'application/x-www-form-urlencoded',
            'Authorization': basicHeader
        });
        const params = new URLSearchParams();

        params.set('grant_type', 'password');
        params.set('client_id', CONSTANTS.clientId);
        params.set('client_secret', CONSTANTS.secret);
     
        const options = new RequestOptions({ headers: headers });

        const promise = new Promise(
            (resolve, reject) => {
                this.http.post(url, params.toString(), options).subscribe(
                    res => {
                        resolve(res);
                    },
                    error => {
                        console.log('User is not authenticated.', error.text());
                        reject(error);
                    }
                );
            }
        );

        return promise;
    }

}
