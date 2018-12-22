import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {User} from "../user/user";


@Injectable({
  providedIn: 'root',
})
export class LoginService {
  private getMeLocation = "/api/user/me";
  private userMe: Observable<User> = null;

  constructor(private http: HttpClient) {
    this.userMe = this.fetchMe();
  }

  private fetchMe(): Observable<User> {
    return this.http.get<User>(this.getMeLocation);
  }

  public me(): Observable<User> {
    return this.userMe;
  }

}
