import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable()
export class AppService {

    authenticated = false;

    constructor(private http: HttpClient) {
        console.log("New service");
    }

    authenticate(credentials, callback) {

        const headers = new HttpHeaders(credentials ? {
            authorization : 'Basic ' + btoa(credentials.username + ':' + credentials.password)
        } : {});

        this.http.get('api/user', {headers: headers}).subscribe(response => {
            console.log(response);
            if (response['name']) {
                console.log("Authenticated");
                this.authenticated = true;
            } else {
                console.log("Logged Out");
                this.authenticated = false;
            }
            return callback && callback();
        }, error => {this.authenticated = false;});

    }

    checkLogin() {
        this.http.get("/api/user").subscribe(response => {
            console.log(response);
            if (response['name']) {
                console.log("Authenticated");
                this.authenticated = true;
            } else {
                console.log("Logged Out");
                this.authenticated = false;
            }
        }, error => {this.authenticated = false;} )
    }

    isAuthenticated(): boolean {
        console.log(this.authenticated);
        return this.authenticated;
    }

}
