import {Component} from "@angular/core";
import {UserService} from "../user.service";
import {User} from "../user";

@Component({
  selector: 'add-friends',
  templateUrl: './add-friends.component.html'
})
export class AddFriendsComponent {
  query: string;
  users: User[] = [];

  cQuery: string = "";

  constructor(private userService: UserService) {
  }

  search(query: string): void {
    if(query) {
      this.userService.searchForNewFriends(query).subscribe(u => this.users = u);
      this.cQuery = query;
    }
  }

  sendRequest(user: User) {
    this.userService.sendFriendshipRequest(user.email).subscribe(resp => this.search(this.cQuery));
  }

}
