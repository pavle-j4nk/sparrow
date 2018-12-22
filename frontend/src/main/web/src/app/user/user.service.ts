import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private getMeLocation = "/user/me";
  private user : User;

  constructor(private http: HttpClient) {
    this.fetchUser().subscribe(u => this.user = u);
  }

  private fetchUser() : Observable<User> {
    return this.http.get<User>(this.getMeLocation);
  }

  public me() : User {
    return this.user;
  }


}
