import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = ['http://localhost:9090/']

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(
    private http: HttpClient
  ) { }

  signup(signupRequest: any): Observable<any> {
    return this.http.post(BASE_URL + "api/v1/auth/signup", signupRequest)
  }

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + "api/v1/auth/signin", loginRequest)
  }

  

  hello(): Observable<any> {
    return this.http.get(BASE_URL + "api/v1/user", {
      headers: this.createAuthorizationHeader()
    });
  }

  Adminhello(): Observable<any> {
    return this.http.get(BASE_URL + "api/v1/admin", {
      headers: this.createAuthorizationHeader()
    });
  }

  private createAuthorizationHeader() {
    const jwtToken = localStorage.getItem('JWT');

    if (jwtToken) {
      return new HttpHeaders().set(
        'Authorization', 'Bearer ' + jwtToken
      )
    } else {
      console.log("JWT token not found in the Local Storage");
    }
    return null;
  }

}
