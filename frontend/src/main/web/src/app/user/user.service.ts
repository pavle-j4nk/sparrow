import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "./user";
import {FriendshipRequest} from "./friendshipRequest";

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private APILocation = "/api/user/";
  private getMeLocation = this.APILocation + "me";
  private friendLocation = this.APILocation + "friends/";
  private friendRequestLocation = this.friendLocation + "request/";
  private getAllRequests = this.friendRequestLocation + "all"
  private search = this.APILocation + "search/";
  private searchCanAddFriendParam = "canAddFriend=true";

  private user: User;

  constructor(private http: HttpClient) {
    this.fetchMe().subscribe(u => this.user = u);
  }

  private fetchMe(): Observable<User> {
    return this.http.get<User>(this.getMeLocation);
  }

  public me(): User {
    return this.user;
  }

  public getUser(id: string): Observable<User> {
    return this.http.get<User>(this.APILocation + id);
  }

  public updateProfile(user: User): Observable<Object> {
    return this.http.post(this.getMeLocation, user);
  }

  public getFriends(): Observable<User[]> {
    return this.http.get<User[]>(this.friendLocation);
  }

  public removeFriend(email: string): Observable<Object> {
    return this.http.delete(this.friendLocation + email);
  }

  public searchForNewFriends(query: string): Observable<User[]> {
    return this.http.get<User[]>(this.search + query + '?' + this.searchCanAddFriendParam);
  }

  public sendFriendshipRequest(email: string): Observable<Object> {
    return this.http.put(this.friendRequestLocation + email, "");
  }

  public getFriendshipRequests(): Observable<FriendshipRequest[]> {
    return this.http.get<FriendshipRequest[]>(this.getAllRequests);
  }

  public declineFriendshipRequest(id: string): Observable<Object> {
    return this.http.post(this.friendRequestLocation + id + "/decline", "");
  }

  public acceptFriendshipRequest(id: string): Observable<Object> {
    return this.http.post(this.friendRequestLocation + id + "/accept", "");
  }

}
