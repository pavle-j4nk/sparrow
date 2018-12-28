import {Component} from "@angular/core";
import {UserService} from "../user.service";
import {User} from "../user";

@Component({
  selector: 'friends',
  templateUrl: './user.friends.component.html'
})
export class UserFriendComponent {
  friends: User[] = [];

  constructor(private userService: UserService) {
    this.loadFriends();
  }

  loadFriends() :void {
    this.userService.getFriends().subscribe(friends => this.friends = friends);
  }

  removeFriend(friend: User) : void {
    this.userService.removeFriend(friend.email).subscribe(v => this.loadFriends());
  }

}
