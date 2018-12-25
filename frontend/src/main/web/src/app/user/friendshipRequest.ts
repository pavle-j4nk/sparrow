import {User} from "./user";

export class FriendshipRequest {
  constructor(public id: number
    ,public sender: User
    ,public receiver: string
    ,public status: string) {
  }
}
