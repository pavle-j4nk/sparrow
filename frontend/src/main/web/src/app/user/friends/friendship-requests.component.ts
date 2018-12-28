import {Component} from "@angular/core";
import {FriendshipRequest} from "../friendshipRequest";
import {UserService} from "../user.service";

@Component({
  selector: 'friendship-requests',
  templateUrl: './friendship-requests.component.html'
})

export class FriendshipRequestsComponent {

  requests: FriendshipRequest[] = [];

  constructor(private userService: UserService) {
    this.loadRequests();
  }

  private loadRequests() {
    this.userService.getFriendshipRequests().subscribe(rs => this.requests = rs);
  }

  accept(request: FriendshipRequest) {
    this.userService.acceptFriendshipRequest(request.sender.email).subscribe(r => this.loadRequests());
  }

  decline(request: FriendshipRequest) {
    this.userService.declineFriendshipRequest(request.sender.email).subscribe(r => this.loadRequests());
  }

}
