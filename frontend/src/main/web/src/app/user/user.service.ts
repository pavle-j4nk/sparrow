import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private APILocation = "/api/user/";
  private getMeLocation = this.APILocation + "me";
  private updateProfileLocation = this.APILocation + "update";

  private user : User;

  constructor(private http: HttpClient) {
    this.fetchMe().subscribe(u => this.user = u);
  }

  private fetchMe() : Observable<User> {
    return this.http.get<User>(this.getMeLocation);
  }

  public me() : User {
    return this.user;
  }

  public getUser(id: string): Observable<User> {
    return this.http.get<User>(this.APILocation + id);
  }

  public updateProfile(user: User): Observable<Object> {
    return this.http.post(this.updateProfileLocation, user);
  }




}
