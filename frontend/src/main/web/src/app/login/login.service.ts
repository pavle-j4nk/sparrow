import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import { catchError, map, tap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private loginUrl = "/perform_login";

  constructor(private http: HttpClient) {
  }

  login(username: String, password: String): Observable<String> {
    if(!username || !password) {
      return Observable.create("Enter username and password.");
    }

    return this.http.post<String>(this.loginUrl, {});
  }

  isAuthenticated() {
    return true;
  }

}
